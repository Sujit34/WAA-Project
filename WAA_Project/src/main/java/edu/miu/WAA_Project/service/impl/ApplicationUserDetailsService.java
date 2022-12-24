package edu.miu.WAA_Project.service.impl;

import edu.miu.WAA_Project.entity.User;
import edu.miu.WAA_Project.exceptions.UserDeactivatedException;
import edu.miu.WAA_Project.exceptions.UserNotExistsException;
import edu.miu.WAA_Project.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsService")
public class ApplicationUserDetailsService implements UserDetailsService {
    UserRepository userRepository;

    public ApplicationUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) throw new UserNotExistsException(); //UsernameNotFoundException("Username could not be found in the system");
        if (user.isDeleted()) throw new UserNotExistsException();
        return new ApplicationUserDetail(user);
    }
}
