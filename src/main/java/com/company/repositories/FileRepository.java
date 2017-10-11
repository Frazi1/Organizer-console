package com.company.repositories;

import com.company.models.json.Json;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static com.company.Helper.writeToFile;

public abstract class FileRepository<T> implements Repository<T> {
    protected final String filePath;
    protected final List<T> TList;

    protected abstract Collection<T> loadData();

    public FileRepository(String filePath) {
        this.filePath = filePath;
        TList = new ArrayList<>();
        TList.addAll(loadData());
    }

    @Override
    public T getItem(Predicate<T> predicate) {
        return TList.stream()
                .filter(predicate)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<T> getItems() {
        return getItems(T -> true);
    }

    @Override
    public List<T> getItems(Predicate<T> predicate) {
        return TList.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    @Override
    public void add(T item) {
        SetId(TList.size() + 1 ,item);
        TList.add(item);
    }

    protected abstract void SetId(Integer id, T item);

    @Override
    public void edit(Integer id, T newObject) {
        //TODO: implement
    }

    @Override
    public boolean remove(Predicate<T> predicate) {
        return TList.removeIf(predicate);
    }

    @Override
    public void saveChanges() {
        Gson gson = Json.getGson();
        String json = gson.toJson(TList);
//        System.out.println(json);
        writeToFile(filePath, json);
    }
}
