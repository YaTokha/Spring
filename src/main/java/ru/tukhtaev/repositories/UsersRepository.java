package ru.tukhtaev.repositories;

import ru.tukhtaev.models.User;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends CrudRepository<User, Long> {
    Optional<User> findOneByEmail(String email);

}
