package com.vijay.order.entity;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.vijay.order.dto.FoodItemDTO;
import com.vijay.order.dto.Restaurant;
import com.vijay.order.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("order")
public class Order {

    private Integer orderId;
    private List<FoodItemDTO> foodItemList;
    private Restaurant restaurant;
    private UserDTO userDTO;
}
