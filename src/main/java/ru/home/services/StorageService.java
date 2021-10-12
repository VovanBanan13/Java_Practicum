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
    /**
     * Добавление товаров на склад, список товаров в json в виде:
     *      [{"toy_1":x}, {"toy_2":x}, {"toy_3":x}, ..., {"toy_n":x}] ,
     *      где "toy_n" - название товара, x - количество товаров на добавление.
     *
     * @param toyList Лист товаров с количеством
     */
    void addToyCount(List<HashMap<String, Integer>> toyList);
}
