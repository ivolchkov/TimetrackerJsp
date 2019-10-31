package project.repository;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<ID, E> {
    boolean save(E entity);

    Optional<E> findById(ID id);

    List<E> findAll();

    void update(E entity);

    boolean deleteById(ID id);
}
