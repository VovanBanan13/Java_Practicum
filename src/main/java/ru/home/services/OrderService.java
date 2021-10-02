package ru.home.services;

import java.util.List;
import ru.home.models.Order;
import ru.home.models.Toy;

public interface OrderService {
    List<Order> findAllOrders();
    Order getById(int id);
    void save(Order order);
    void delete(int id);
    void addToy(Toy toy, int count);
}
