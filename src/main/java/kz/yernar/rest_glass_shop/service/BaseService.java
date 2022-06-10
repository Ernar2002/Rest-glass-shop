package kz.yernar.rest_glass_shop.service;

import java.util.List;

public interface BaseService<T> {

    void save(T t);

    void update(Long id, T t);

    T getById(Long id);

    void delete(Long id);

    List<T> getAll();
}