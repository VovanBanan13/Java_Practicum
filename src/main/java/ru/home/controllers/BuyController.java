package ru.home.controllers;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.home.dto.OrderDTO;
import ru.home.dto.ShopListDTO;
import ru.home.mappers.OrderMapper;
import ru.home.mappers.ShopListMapper;
import ru.home.models.Order;
import ru.home.models.ShopList;
import ru.home.models.Toy;
import ru.home.models.User;
import ru.home.services.OrderService;
import ru.home.services.ShopListService;

@RestController
@RequestMapping("/buy")
public class BuyController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final ShopListService shopListService;
    private final ShopListMapper shopListMapper;

    @Autowired
    public BuyController(OrderService orderService, OrderMapper orderMapper, ShopListService shopListService, ShopListMapper shopListMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
        this.shopListService = shopListService;
        this.shopListMapper = shopListMapper;
    }

    @PostMapping(produces = "application/json")
    //public ResponseEntity<ShopListDTO> createBuy(@RequestBody List<ShopList> shopList) {
    public void createBuy(@RequestBody Map<Toy, Integer> buyList, User user) {
        Order order = new Order();
        order.setDate(new Date());
        order.setTime(new Time(1));
        order.setUser(user);
        orderService.save(order);

        for (Map.Entry<Toy, Integer> buyToy : buyList.entrySet()) {
            ShopList shopList = new ShopList();
            shopList.setOrder(order);
            shopList.setToy(buyToy.getKey());
            shopList.setCount(buyToy.getValue());

            shopListService.save(shopList);
        }

        //return new ResponseEntity<>(orderMapper.EntityToDto(order), HttpStatus.CREATED);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<OrderDTO>> getAllBuy() {
        List<Order> orders = orderService.findAllOrders();

        return new ResponseEntity<>(orderMapper.toOrderDTOS(orders), HttpStatus.OK);
    }
}
