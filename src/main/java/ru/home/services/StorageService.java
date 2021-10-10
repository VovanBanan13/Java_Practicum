package ru.home.services;

import java.util.HashMap;
import java.util.List;
import ru.home.models.Storage;

public interface StorageService {
    List<Storage> findAllStorages();
    Storage getById(int id);
    Storage getByToyName(String toyName);
    void save(Storage storage);
    void delete(int id);
    Storage update(int id, Storage storage);
    void addToyCount(List<HashMap<String, Integer>> toyList);
}
