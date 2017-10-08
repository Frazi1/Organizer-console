package com.company.repositories;

import java.util.List;
import java.util.function.Predicate;

public interface Repository<T> {
    T getItem(Integer id);
    T getItem(Predicate<T> predicate);

    List<T> getItems();
    List<T> getItems(Predicate<T> predicate);

    void add(T item);
    void edit(Integer id, T newObject);

    boolean remove(Integer id);
    boolean remove(Predicate<T> predicate);

    void saveChanges();
}
