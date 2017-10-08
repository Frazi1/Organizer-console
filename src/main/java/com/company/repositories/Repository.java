package com.company.repositories;

import java.util.List;
import java.util.function.Predicate;

public interface Repository<T> {
    T getItem(Integer id);
    T getItem(Predicate<T> predicate);

    List<T> getItems();
    List<T> getItems(Predicate<T> predicate);

    void add(T item);

    void remove(T item);
    void remove(Predicate<T> predicate);

    void saveChanges();
}
