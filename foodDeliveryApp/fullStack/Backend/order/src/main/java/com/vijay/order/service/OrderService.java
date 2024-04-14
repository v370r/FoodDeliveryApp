package com.vijay.order.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vijay.order.dto.OrderDTO;
import com.vijay.order.dto.OrderDTOFromFE;
import com.vijay.order.dto.UserDTO;
import com.vijay.order.entity.Order;
import com.vijay.order.mapper.OrderMapper;
import com.vijay.order.repository.OrderRepository;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    SequenceGenerator sequenceGenerator;

    @Autowired
    RestTemplate restTemplate;

    public OrderDTO saveOrderInDb(OrderDTOFromFE orderDetails) {

        Integer newOrderId = sequenceGenerator.generateNextOrderId();
        UserDTO userDTO = fetchOrderDetailsFromUserId(orderDetails.getUserId());
        Order orderToSave = new Order(newOrderId, orderDetails.getFoodItemList(), orderDetails.getRestaurant(),
                userDTO);
        orderRepository.save(orderToSave);
        return OrderMapper.INSTANCE.mapOrderToOrderDTO(orderToSave);
    }

    private UserDTO fetchOrderDetailsFromUserId(Integer userId) {

        return restTemplate.getForObject("http://USER-SERVICE/user/fetchUserById/" + userId, UserDTO.class);

    }

}
