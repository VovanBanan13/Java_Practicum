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
import ru.home.models.Toy;
import ru.home.models.User;
import ru.home.services.BuyService;
import ru.home.services.OrderService;
import ru.home.services.ShopListService;
import ru.home.services.ToyService;
import ru.home.services.UserService;

@Service
@Transactional
public class BuyServiceImpl implements BuyService {
    private final UserService userService;
    private final OrderService orderService;
    private final ToyService toyService;
    private final ShopListService shopListService;

    @Autowired
    public BuyServiceImpl(UserService userService, OrderService orderService, ToyService toyService, ShopListService shopListService) {

        this.userService = userService;
        this.orderService = orderService;
        this.toyService = toyService;
        this.shopListService = shopListService;
    }

    @Override
    public void saveBuy(int id, List<HashMap<String, Integer>> buyList) {
        Order order = new Order();
        order.setDate(new Date());
        order.setTime(new Time(1));
        User user = userService.getById(id);
        order.setUser(user);
        orderService.save(order);

        for (HashMap<String, Integer> buyListMap : buyList) {
            for (Map.Entry<String, Integer> buyToy : buyListMap.entrySet()) {
                ShopList shopList = new ShopList();
                shopList.setOrder(order);
                Toy toy = toyService.getByName(buyToy.getKey());
                shopList.setToy(toy);
                shopList.setCount(buyToy.getValue());

                shopListService.save(shopList);
            }
        }
    }

}
