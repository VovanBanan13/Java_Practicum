package ru.home.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.home.dto.OrderDTO;
import ru.home.mappers.OrderMapper;
import ru.home.models.Order;
import ru.home.services.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<Order> orders = orderService.findAllOrders();

        return new ResponseEntity<>(orderMapper.toOrderDTOS(orders), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable("id") Integer id) {
        Order order = orderService.getById(id);

        return new ResponseEntity<>(orderMapper.EntityToDto(order), HttpStatus.OK);
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody Order order) {
        orderService.save(order);

        return new ResponseEntity<>(orderMapper.EntityToDto(order), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable("id") Integer id, @RequestBody Order order) {
        Order changedOrder = orderService.getById(id);

        changedOrder.setDate(order.getDate());
        changedOrder.setTime(order.getTime());
        changedOrder.setUser(order.getUser());

        orderService.save(changedOrder);
        return new ResponseEntity<>(orderMapper.EntityToDto(changedOrder), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<OrderDTO> deleteOrder(@PathVariable("id") Integer id) {
        Order order = orderService.getById(id);

        orderService.delete(id);

        return new ResponseEntity<>(orderMapper.EntityToDto(order),HttpStatus.NO_CONTENT);
    }
}
