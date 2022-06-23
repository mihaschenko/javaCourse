package ru.javawebinar.topjava.repository;

import java.util.List;

public interface Repository<T> {
    void add(T object);
    void delete(int id);
    T get(int id);
    List<T> getAll();
}
