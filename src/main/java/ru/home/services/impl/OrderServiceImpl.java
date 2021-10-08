package ru.home.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.home.exceptions.ObjectNotFoundAdvice;
import ru.home.models.Order;
import ru.home.repositories.OrderRepository;
import ru.home.services.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> findAllOrders() {
        List<Order> orders = orderRepository.findAll();
        if(orders.isEmpty()) {
            throw new ObjectNotFoundAdvice();
        } else
            return orders;
    }

    @Override
    public Order getById(int id) {
        return orderRepository.findById(id).orElseThrow(() -> new ObjectNotFoundAdvice());
    }

    @Override
    public void save(Order order) {
        if(order.getDate()==null || order.getTime()==null || order.getUser()==null)
            throw new ObjectNotFoundAdvice();
        this.orderRepository.save(order);
    }

    @Override
    public void delete(int id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Order update(int id, Order order) {
        Order changedOrder = orderRepository.findById(id).orElseThrow(() -> new ObjectNotFoundAdvice());
        changedOrder.setDate(order.getDate());
        changedOrder.setTime(order.getTime());
        changedOrder.setUser(order.getUser());
        this.orderRepository.save(changedOrder);
        return changedOrder;
    }
}
