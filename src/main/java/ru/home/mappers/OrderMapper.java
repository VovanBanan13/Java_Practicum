package ru.home.mappers;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.home.dto.OrderDto;
import ru.home.models.Order;


@Component
public class OrderMapper {
    @Autowired
    ModelMapper modelMapper;

    public OrderDto entityToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setDate(order.getDate());
        orderDto.setTime(order.getTime());
        orderDto.setUser_id(order.getUser().getId());
        return orderDto;
    }

    public List<OrderDto> toOrderDtos(List<Order> orders) {
        List<OrderDto> orderDtos = new ArrayList<>();
        for (Order order : orders) {
            OrderDto orderDto = new OrderDto();
            orderDto.setId(order.getId());
            orderDto.setDate(order.getDate());
            orderDto.setTime(order.getTime());
            orderDto.setUser_id(order.getUser().getId());
            orderDtos.add(orderDto);
        }
        return orderDtos;
    }
}
