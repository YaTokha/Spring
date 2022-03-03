package ru.tukhtaev.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.tukhtaev.models.User;
import ru.tukhtaev.repositories.UsersRepository;

import java.time.LocalDateTime;

public class UsersServicesImpl implements UsersServices {


    private UsersRepository usersRepository;
    private PasswordEncoder passwordEncoder;
    private MailService mailService;

    public UsersServicesImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder, MailService mailService) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailService = mailService;
    }

    public void signUp(String firstName, String lastName, String email, String password) {
        User user = User.builder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .hashPassword(passwordEncoder.encode(password))
                .build();
        usersRepository.save(user);
    }

    public void signIn(String email, String password) {
        usersRepository.findOneByEmail(email).ifPresent(user -> {
            if(passwordEncoder.matches(password, user.getHashPassword())){
                mailService.sendMail(email, "был выполнен вход в " + LocalDateTime.now().toString());
            }
        });
    }
}
