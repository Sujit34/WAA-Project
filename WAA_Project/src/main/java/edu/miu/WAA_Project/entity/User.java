package edu.miu.WAA_Project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "users")
@Component
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Address address;

    private boolean isDeleted = Boolean.FALSE;

    @Column(name = "user_type", insertable = false, updatable = false)
    private String userType;

    private boolean isActivated = Boolean.FALSE ;

    private boolean isLocked;

    private int invalidLoginCount = 0;

    private LocalDateTime LastLockTime;

    private boolean isEmailVerified;

    private String emailVerificationToken;

    private LocalDateTime emailVerificationTokenExpiry;
    private int emailVerificationAttempts = 0;

}
