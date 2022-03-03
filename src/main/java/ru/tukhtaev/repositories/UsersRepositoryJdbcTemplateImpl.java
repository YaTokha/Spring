package ru.tukhtaev.repositories;

import lombok.Getter;
import lombok.Setter;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.tukhtaev.models.User;

import javax.sql.DataSource;
import java.util.*;

@Getter
@Setter

public class UsersRepositoryJdbcTemplateImpl implements UsersRepository {


    //language=SQL
    private static final String SQL_FIND_ONE_BY_EMAIL = "SELECT * FROM account WHERE email = ? limit 1";

    //language=SQL
    private static final String SQL_INSERT = "insert into account(first_name, last_name, email, hash_password) values (:first_name, :last_name, :email, :hash_password)";


//    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate jdbcTemplate;
    public UsersRepositoryJdbcTemplateImpl(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
//       this.jdbcTemplate = template;
       this.jdbcTemplate = namedParameterJdbcTemplate;
    }



    private RowMapper<User> userRowMapper = (row, rowNumber) ->
         User.builder()
                .firstName(row.getString("first_name"))
                .lastName(row.getString("last_name"))
                .email(row.getString("email"))
                 .hashPassword(row.getString("hash_password"))
                .build();



    @Override
    public Optional<User> findOneByEmail(String email) {
        try {
            return Optional.of(jdbcTemplate.queryForObject(SQL_FIND_ONE_BY_EMAIL, Collections.singletonMap("email", email), userRowMapper));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }


    @Override
    public void save(User entity) {
        Map<String, Object> params = new HashMap<>();
        params.put("firstName", entity.getFirstName());
        params.put("email", entity.getEmail());
        params.put("lastName", entity.getLastName());
        params.put("hashPassword", entity.getHashPassword());
        jdbcTemplate.update(SQL_INSERT, params);
    }

    @Override
    public void delete(User id) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public Optional<User> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
