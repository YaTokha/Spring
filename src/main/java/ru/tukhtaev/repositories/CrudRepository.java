package ru.tukhtaev.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, ID> {
    void save(T entity);
    void delete(T id);
    void update(T entity);

    Optional<T> findById(ID id);
    List<T> findAll();
}
