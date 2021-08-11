package jp.mikunika.SpringBootInsurance.service;

import java.util.List;

public interface BaseService<T> {
    List<T> getAll();
    T getOne(Long id);
    T save(T entity);
    T update(Long id, T entityNew);
    void delete(Long id);
}
