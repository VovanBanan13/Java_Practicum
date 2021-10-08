package ru.home.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.home.mappers.OrderMapper;
import ru.home.mappers.ShopListMapper;
import ru.home.mappers.UserMapper;
import ru.home.models.Order;
import ru.home.models.ShopList;
import ru.home.models.Toy;
import ru.home.models.User;
import ru.home.services.OrderService;
import ru.home.services.ShopListService;
import ru.home.services.ToyService;
import ru.home.services.UserService;

@RestController
@RequestMapping("/buy")
@Api(value="buy")
public class BuyController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final ShopListService shopListService;
    private final ShopListMapper shopListMapper;
    private final UserService userService;
    private final UserMapper userMapper;
    private final ToyService toyService;

    @Autowired
    public BuyController(OrderService orderService, OrderMapper orderMapper, ShopListService shopListService, ShopListMapper shopListMapper, UserService userService, UserMapper userMapper, ToyService toyService) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
        this.shopListService = shopListService;
        this.shopListMapper = shopListMapper;
        this.userService = userService;
        this.userMapper = userMapper;
        this.toyService = toyService;
    }

    @ApiOperation(value = "Add a new buy information")
    @PostMapping(value = "/{id}", produces = "application/json")
    //public ResponseEntity<ShopListDto> createBuy(@RequestBody List<ShopList> shopList) {
    //public void createBuy(@RequestBody List<ShopList> buyList, User user) {
    public void createBuy(@PathVariable("id") Integer id, @RequestBody List<HashMap<String, Integer>> buyList) {
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

        //return new ResponseEntity<>(orderMapper.EntityToDto(order), HttpStatus.CREATED);
    }

//    @ApiOperation(value = "View buy information (list of orders)")
//    @GetMapping(produces = "application/json")
//    public ResponseEntity<List<OrderDto>> getAllBuy() {
//        List<Order> orders = orderService.findAllOrders();
//
//        return new ResponseEntity<>(orderMapper.toOrderDtos(orders), HttpStatus.OK);
//    }
}
