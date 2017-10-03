package com.company.repositories;

import java.util.function.Predicate;

public interface Repository<T> {
    T getItem(Integer id);
    T getItem(Predicate<T> predicate);

    Iterable<T> getItems();
    Iterable<T> getItems(Predicate<T> predicate);

    void add(T item);

    void remove(T item);
    void remove(Predicate<T> predicate);
}
