package com.vijay.restaurantlisting.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.vijay.restaurantlisting.dto.RestaurantDTO;
import com.vijay.restaurantlisting.entity.Restaurant;

@Mapper
public interface RestaurantMapper {

    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    // @Mapping(target = "id", ignore = true)
    Restaurant mapRestaurantDTOtoRestaurant(RestaurantDTO restaurantDTO);

    RestaurantDTO mapRestauranttoRestaurantDTO(Restaurant restaurant);

}
