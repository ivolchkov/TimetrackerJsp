package project.service.validator;

public interface Validator<E> {
    void validate(E entity);
}
