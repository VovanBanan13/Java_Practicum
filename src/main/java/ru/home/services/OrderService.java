package ru.home.services;

import java.util.List;
import ru.home.models.Order;

public interface OrderService {
    List<Order> findAllOrders();
    Order getById(int id);
    void save(Order order);
    void delete(int id);
}
