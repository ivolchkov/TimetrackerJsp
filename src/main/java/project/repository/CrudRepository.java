package project.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface CrudRepository<ID extends Serializable, E> {
    boolean save(E entity);

    Optional<E> findById(ID id);

    List<E> findAll(Integer offset, Integer amount);

    Integer findAmountOfRows();

    void update(E entity);

    default boolean deleteById(ID id) {
        throw new UnsupportedOperationException();
    }
}
