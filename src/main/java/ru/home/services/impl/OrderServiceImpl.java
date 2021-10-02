package ru.home.services.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.home.exceptions.ObjectNotFoundAdvice;
import ru.home.models.Order;
import ru.home.models.ShopList;
import ru.home.models.Toy;
import ru.home.repositories.OrderRepository;
import ru.home.services.OrderService;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private List<ShopList> shopLists;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, List<ShopList> shopLists) {
        this.orderRepository = orderRepository;
        this.shopLists = shopLists;
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

    private ShopList findToyById(int id) {
        for (ShopList toyList : this.shopLists) {
            if (toyList.getToy().getId() == id) {
                return toyList;
            }
        }
        return null;
    }

    public void addToy(Toy toy, int count) {
        ShopList toyList = this.findToyById(toy.getId());

        if (toyList == null) {
            toyList = new ShopList();
            toyList.setCount(0);
            toyList.setToy(toy);
            this.shopLists.add(toyList);
        }
        int newCount = toyList.getCount() + count;
        if (newCount <= 0) {
            this.shopLists.remove(toyList);
        } else {
            toyList.setCount(newCount);
        }
    }
}
