package ru.tukhtaev;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.tukhtaev.front.Front;
import ru.tukhtaev.front.FrontImpl;
import ru.tukhtaev.repositories.UsersRepository;
import ru.tukhtaev.repositories.UsersRepositoryJdbcTemplateImpl;
import ru.tukhtaev.services.MailService;
import ru.tukhtaev.services.MailServiceIMockmpl;
import ru.tukhtaev.services.UsersServices;
import ru.tukhtaev.services.UsersServicesImpl;

public class Application {
    public static void main(String[] args) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/postgres");
        config.setDriverClassName("org.postgresql.Driver");
        config.setUsername("postgres");
        config.setPassword("SilentHill_302");
        config.setMaximumPoolSize(20);
        HikariDataSource dataSource = new HikariDataSource(config);

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        UsersRepository usersRepository = new UsersRepositoryJdbcTemplateImpl(namedParameterJdbcTemplate);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        MailService mailService = new MailServiceIMockmpl();
        UsersServices usersServices = new UsersServicesImpl(usersRepository, passwordEncoder, mailService);
        Front front = new FrontImpl(usersServices);
        front.run();


    }
}
