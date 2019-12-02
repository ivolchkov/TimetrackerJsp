package com.ua.timetracking.service.validator;

public interface Validator<E> {
    void validate(E entity);
}
