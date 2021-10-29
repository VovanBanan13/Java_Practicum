package ru.home.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
import ru.home.dto.OrderDto;
import ru.home.mappers.OrderMapper;
import ru.home.models.Order;
import ru.home.services.OrderService;

@RestController
@RequestMapping("/order")
@Api(value="order")
public class OrderController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @Autowired
    public OrderController(OrderService orderService, OrderMapper orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @ApiOperation(value = "View a list of orders")
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        List<Order> orders = orderService.findAllOrders();

        return new ResponseEntity<>(orderMapper.toOrderDtos(orders), HttpStatus.OK);
    }

    @ApiOperation(value = "View information about the order")
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<OrderDto> getOrder(@PathVariable("id") Integer id) {
        Order order = orderService.getById(id);

        return new ResponseEntity<>(orderMapper.entityToDto(order), HttpStatus.OK);
    }

    @ApiOperation(value = "Add a new order")
    @PostMapping(produces = "application/json")
    public ResponseEntity<OrderDto> createOrder(@RequestBody Order order) {
        orderService.save(order);

        return new ResponseEntity<>(orderMapper.entityToDto(order), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update order information")
    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable("id") Integer id, @RequestBody Order order) {
        Order changedOrder = orderService.update(id, order);

        return new ResponseEntity<>(orderMapper.entityToDto(changedOrder), HttpStatus.OK);
    }

    @ApiOperation(value = "Remote order")
    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<OrderDto> deleteOrder(@PathVariable("id") Integer id) {
        Order order = orderService.getById(id);

        orderService.delete(id);

        return new ResponseEntity<>(orderMapper.entityToDto(order),HttpStatus.NO_CONTENT);
    }
}
