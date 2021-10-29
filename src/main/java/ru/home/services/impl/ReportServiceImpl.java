package ru.home.services.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.home.models.Order;
import ru.home.models.ShopList;
import ru.home.services.OrderService;
import ru.home.services.ReportService;
import ru.home.services.ShopListService;

@Service
@Transactional
public class ReportServiceImpl implements ReportService {
    private final OrderService orderService;
    private final ShopListService shopListService;

    public ReportServiceImpl(OrderService orderService, ShopListService shopListService) {
        this.orderService = orderService;
        this.shopListService = shopListService;
    }

    @Override
    public double getProfit() {
        double profit = 0;
        List<Order> orders = orderService.findAllOrders();
        for (Order order : orders) {
            profit = profit + order.getAmount();
        }
        return profit;
    }

    @Override
    public double getAverageCheck() {
        double sum = 0;
        int count = 0;
        List<Order> orders = orderService.findAllOrders();
        for (Order order : orders) {
            sum = sum + order.getAmount();
            count = count + 1;
        }
        double avg = sum / count;
        avg = Math.round(avg * 100);
        avg = avg/100;

        return avg;
    }

    @Override
    public Map<String, Integer> getToysSold() {
        List<ShopList> shopLists = shopListService.findAllShopLists();
        Map<String, Integer> toys = new HashMap<>();
        for (ShopList shopList : shopLists) {
            if (toys.keySet().contains(shopList.getToy().getName())) {
                toys.put(shopList.getToy().getName(), toys.get(shopList.getToy().getName()) + shopList.getCount());
            } else {
                toys.put(shopList.getToy().getName(), shopList.getCount());
            }
        }
        return toys;
    }

    @Override
    public Map<String, Integer> getToysSoldByTime(Date startDate, Date endDate) {
        List<ShopList> shopLists = shopListService.findAllShopLists();
        Map<String, Integer> toys = new HashMap<>();
        for (ShopList shopList : shopLists) {
            if (shopList.getOrder().getDate().after(startDate) && shopList.getOrder().getDate().before(endDate)) {
                if (toys.keySet().contains(shopList.getToy().getName())) {
                    toys.put(shopList.getToy().getName(), toys.get(shopList.getToy().getName()) + shopList.getCount());
                } else {
                    toys.put(shopList.getToy().getName(), shopList.getCount());
                }
            }
        }
        return toys;
    }
}
