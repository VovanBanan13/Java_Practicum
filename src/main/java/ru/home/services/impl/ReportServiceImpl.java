package ru.home.services.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.home.models.Order;
import ru.home.services.OrderService;
import ru.home.services.ReportService;

@Service
@Transactional
public class ReportServiceImpl implements ReportService {
    private final OrderService orderService;

    public ReportServiceImpl(OrderService orderService) {
        this.orderService = orderService;
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
}
