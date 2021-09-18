package ru.home.mappers;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.home.dto.OrderDTO;
import ru.home.models.Order;


@Component
public class OrderMapper {
    @Autowired
    ModelMapper modelMapper;

    public OrderDTO EntityToDto(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(order.getId());
        orderDTO.setDate(order.getDate());
        orderDTO.setTime(order.getTime());
        orderDTO.setUser_id(order.getUser().getId());
        return orderDTO;
    }

    public List<OrderDTO> toOrderDTOS(List<Order> orders) {
        List<OrderDTO> orderDTOS = new ArrayList<>();
        for (Order order : orders) {
            OrderDTO orderDTO = new OrderDTO();
            orderDTO.setId(order.getId());
            orderDTO.setDate(order.getDate());
            orderDTO.setTime(order.getTime());
            orderDTO.setUser_id(order.getUser().getId());
            orderDTOS.add(orderDTO);
        }
        return orderDTOS;
    }
}
