package ru.home.services.impl;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.home.models.Order;
import ru.home.models.ShopList;
import ru.home.models.Storage;
import ru.home.models.Toy;
import ru.home.models.User;
import ru.home.services.BuyService;
import ru.home.services.OrderService;
import ru.home.services.ShopListService;
import ru.home.services.StorageService;
import ru.home.services.ToyService;
import ru.home.services.UserService;

@Service
@Transactional
public class BuyServiceImpl implements BuyService {
    private final UserService userService;
    private final OrderService orderService;
    private final ToyService toyService;
    private final ShopListService shopListService;
    private final StorageService storageService;

    @Autowired
    public BuyServiceImpl(UserService userService, OrderService orderService, ToyService toyService, ShopListService shopListService, StorageService storageService) {

        this.userService = userService;
        this.orderService = orderService;
        this.toyService = toyService;
        this.shopListService = shopListService;
        this.storageService = storageService;
    }

    @Override
    public double saveBuy(int id, List<HashMap<String, Integer>> buyList) {
        Order order = new Order();
        order.setDate(new Date());
        order.setTime(new Time(1));
        User user = userService.getById(id);
        order.setUser(user);
        orderService.save(order);
        double sumAmount = 0;
        for (HashMap<String, Integer> buyListMap : buyList) {
            for (Map.Entry<String, Integer> buyToy : buyListMap.entrySet()) {
                ShopList shopList = new ShopList();
                shopList.setOrder(order);
                Toy toy = toyService.getByName(buyToy.getKey());
                double amount = toy.getPrice() * buyToy.getValue();
                shopList.setToy(toy);
                shopList.setCount(buyToy.getValue());
                sumAmount = sumAmount + amount;
                shopListService.save(shopList);
                Storage storage = storageService.getByToyName(buyToy.getKey());
                storage.setCount(storage.getCount() - buyToy.getValue());
                this.storageService.save(storage);
            }
        }
        return sumAmount;
    }

}
