package project.repository;

import project.domain.Entity;

import java.util.List;
import java.util.Optional;

public interface CRUDRepository<ID, E extends Entity> {
    boolean save(E entity);

    Optional<E> findById(ID id);
    List<E> findAll();

    void update(E entity);

    boolean deleteById(ID id);
}
