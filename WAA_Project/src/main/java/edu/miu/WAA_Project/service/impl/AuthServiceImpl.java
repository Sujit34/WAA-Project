package edu.miu.WAA_Project.service.impl;

import edu.miu.WAA_Project.entity.Faculty;
import edu.miu.WAA_Project.entity.PasswordResetToken;
import edu.miu.WAA_Project.entity.Student;
import edu.miu.WAA_Project.entity.User;
import edu.miu.WAA_Project.entity.dto.request.EmailVerificationRequest;
import edu.miu.WAA_Project.entity.dto.request.LoginRequest;
import edu.miu.WAA_Project.entity.dto.request.RegisterRequest;
import edu.miu.WAA_Project.entity.dto.response.GenericActivityResponse;
import edu.miu.WAA_Project.entity.dto.response.LoginResponse;
import edu.miu.WAA_Project.entity.dto.response.PasswordResetResponse;
import edu.miu.WAA_Project.exceptions.*;
import edu.miu.WAA_Project.repository.FacultyRepositiry;
import edu.miu.WAA_Project.repository.PasswordResetRepository;
import edu.miu.WAA_Project.repository.StudentRepository;
import edu.miu.WAA_Project.repository.UserRepository;
import edu.miu.WAA_Project.service.AuthService;
import edu.miu.WAA_Project.service.EmailService;
import edu.miu.WAA_Project.service.UserService;
import edu.miu.WAA_Project.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final int MAX_VERIFICATION_ATTEMPTS = 6;
    private final int MAX_LOGIN_ATTEMPTS = 6;
    private final int VERIFICATION_TOKEN_LIFE = 30;

    private final UserRepository userRepository;
    private static final int EXPIRATION = 60 * 24;
    private final ModelMapper modelMapper;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JWTUtil jwtUtil;
    private final UserService userService;
    private final PasswordResetRepository passwordResetRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final StudentRepository studentRepository;
    private final FacultyRepositiry facultyRepositiry;


    @Override
    public void registerStudent(RegisterRequest registerRequest) {
        registerInternal(registerRequest, studentRepository, Student.class);
    }

    @Override
    public void registerFaculty(RegisterRequest registerRequest) {
        registerInternal(registerRequest, facultyRepositiry, Faculty.class);
    }

    private void registerInternal(RegisterRequest registerRequest, CrudRepository repository, Class<? extends User> type) {
        registerRequest.setPassword(bCryptPasswordEncoder.encode(registerRequest.getPassword()));
        User user = modelMapper.map(registerRequest, type);

        generateAndSetTokenDetails(user);
        repository.save(user);
        sendVerificationEmail(user);
    }

    public void generateAndSetTokenDetails(User user) {
        Random random = new Random();
        String token = String.valueOf(random.nextInt(100000, 1000000));

        user.setEmailVerificationToken(token);
        user.setEmailVerificationTokenExpiry(LocalDateTime.now().plusMinutes(VERIFICATION_TOKEN_LIFE));

        user.setEmailVerificationAttempts(0);
        user.setActivated(true);

    }

    private void sendVerificationEmail(User user) {

        String html = "<html><body><p>" + "Your verification PIN for the email address: " + user.getEmailVerificationToken() + "</p>";
        String html2 = "<p>You can verify your account by using this <a href= 'http://localhost:3000/verify-email'>URL</a></p></body></html>";
        String html3 = html + html2;
        emailService.sendWithHTMLBody(user.getEmail(), "Verification Token", html3);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
         try{
               authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
               ));
           }
           catch(Exception ex){
               User user = userService.getUserByEmailId(loginRequest.getEmail());
               if(user.getInvalidLoginCount()==MAX_LOGIN_ATTEMPTS){
                   user.setLocked(Boolean.TRUE);
                   user.setLastLockTime(LocalDateTime.now());
               }
               user.setInvalidLoginCount(user.getInvalidLoginCount() +1);
               userRepository.save(user);
               throw new InvalidCredentialException();
           }

        UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getEmail());

        String email = userDetails.getUsername();

        User user = userService.getUserByEmailId(email);

        if (user == null || user.isDeleted()) {
            throw new UserNotExistsException();
        }
        if (!user.isEmailVerified()) {
            throw new UserNotVerifiedException();
        }
        if(!user.isActivated()){
            throw new UserNotActiveException();
        }
        if(user.getLastLockTime() != null && Duration.between(user.getLastLockTime(),LocalDateTime.now()).toMinutes()>15){
            user.setLastLockTime(null);
            user.setLocked(Boolean.FALSE);
            user.setInvalidLoginCount(0);
        }
        if(user.isLocked()){
            throw new UserIsLockedException();
        }
        if(user.getInvalidLoginCount()> MAX_LOGIN_ATTEMPTS){
            throw new UserInvalidLoginAttemptCount();
        }


        String accessToken = jwtUtil.generateAccessToken(userDetails);
        String refreshToken = jwtUtil.generateRefreshToken(userDetails);

        return new LoginResponse(accessToken, refreshToken, user.getUserType(), user.getFirstName() + " " + user.getLastName(), user.getEmail());
    }
    @Override
    public PasswordResetResponse resetPassword(String email) {
        User user = userRepository.findByEmail(email);

        if (user == null)
            throw new UserNotExistsException();

        if (!user.isDeleted() && user.isEmailVerified()) {
            Random random = new Random();
            String token = String.valueOf(random.nextInt(100000, 1000000));
            createPasswordResetTokenForUser(user, token);

            String html = "<p>You can change your password using the <a href='http://localhost:3000/change-password?changeToken=" + token + "'>URL</a><p>";

            emailService.sendWithHTMLBody(
                    email,
                    "Reset Password",
                    html
            );

            return new PasswordResetResponse(token);
        }

        throw new UserNotVerifiedException();
    }

    @Override
    public GenericActivityResponse verifyEmail(EmailVerificationRequest emailVerificationRequest) {
        User user = userRepository.findByEmail(emailVerificationRequest.getEmail());

        if (user.getEmailVerificationAttempts() >= MAX_VERIFICATION_ATTEMPTS)
            return new GenericActivityResponse(false, "Too many incorrect attempts");

        if (user.getEmailVerificationTokenExpiry().isBefore(LocalDateTime.now()))
            return new GenericActivityResponse(false, "Expired token. Please send a new one");

        boolean tokenMatch = user
                .getEmailVerificationToken()
                .equals(emailVerificationRequest.getToken());

        if (!tokenMatch) {
            user.setEmailVerificationAttempts(user.getEmailVerificationAttempts() + 1);

            userRepository.save(user);
            return new GenericActivityResponse(false, "Invalid token");
        }

        user.setEmailVerified(true);

        userRepository.save(user);
        return new GenericActivityResponse(true, "Verification successful");
    }

    @Override
    public void createPasswordResetTokenForUser(User user, String token) {
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setToken(token);
        passwordResetToken.setUser(user);

        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, EXPIRATION);
        passwordResetToken.setExpiryDate(new Date(cal.getTime().getTime()));
        passwordResetRepository.save(passwordResetToken);
    }

    @Override
    public Optional<User> getUserByPasswordResetToken(String token) {
        return Optional.ofNullable(passwordResetRepository.findByToken(token).getUser());
    }

    @Override
    public void changeUserPassword(User user, String password) {
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
    }

    @Override
    public void changeUserPasswordById(int id, String password) {
        userRepository.findById(id).ifPresent((user) -> {
            changeUserPassword(user, password);
        });
    }

    public void resendVerificationToken(String email) {
        User user = userRepository.findByEmail(email);

        generateAndSetTokenDetails(user);
        userRepository.save(user);
        sendVerificationEmail(user);
    }
}
