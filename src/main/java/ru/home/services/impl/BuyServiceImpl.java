package ru.home.services.impl;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
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

    /**
     * Покупка товаров, id передается из url (id покупателя), список товаров в json в виде:
     *      [{"toy_1":x}, {"toy_2":x}, {"toy_3":x}, ..., {"toy_n":x}] ,
     *      где "toy_n" - название товара, x - количество товаров на покупку.
     * При выполнении создается новый ордер, создаются записи в shop_list по каждому товару,
     *      вычитается количество товаров со склада.
     *
     * @param id Покупатель
     * @param buyList Лист товаров с количеством
     * @return sumAmount - Общая стоимость покупки
     */
    @Override
    public double saveBuy(int id, List<HashMap<String, Integer>> buyList) {
        Order order = new Order();
        Date dateRaw = new Date();
        DateFormat dateStr = new SimpleDateFormat("dd.MM.yyyy");
        DateFormat timeStr = new SimpleDateFormat("HH:mm:ss");
        Date onlyDate = null;
        Date onlyTime = null;
        try {
            onlyDate = new SimpleDateFormat("dd.MM.yyyy").parse(dateStr.format(dateRaw));
            onlyTime = new SimpleDateFormat("HH:mm:ss").parse(timeStr.format(dateRaw));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        order.setDate(onlyDate);
        order.setTime(onlyTime);
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
        order.setAmount(sumAmount);
        orderService.save(order);
        return sumAmount;
    }

}
