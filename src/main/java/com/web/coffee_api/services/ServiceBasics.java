package com.web.coffee_api.services;

import java.util.List;

public interface ServiceBasics <T> {
    List<T> findAll();
    T findById(Long id);
    T insert(T object);
    void deleteById(Long id);
    T updateById(Long id, T object);
}
