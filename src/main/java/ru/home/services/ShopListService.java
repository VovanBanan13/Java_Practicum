package ru.home.services;

import java.util.List;
import ru.home.models.ShopList;

public interface ShopListService {
    List<ShopList> findAllShopLists();
    ShopList getById(int id);
    void save(ShopList shopList);
    void delete(int id);
    ShopList update(int id, ShopList shopList);
}
