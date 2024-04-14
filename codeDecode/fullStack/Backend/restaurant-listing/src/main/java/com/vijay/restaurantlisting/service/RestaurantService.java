package com.vijay.restaurantlisting.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vijay.restaurantlisting.dto.RestaurantDTO;
import com.vijay.restaurantlisting.entity.Restaurant;
import com.vijay.restaurantlisting.mapper.RestaurantMapper;
import com.vijay.restaurantlisting.repository.RestaurantRepository;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<RestaurantDTO> findAllRestaurants() {

        List<Restaurant> restaurants = restaurantRepository.findAll(); // Need to map from entity to DTOneed mapper
        List<RestaurantDTO> restaurantDTOList = restaurants.stream()
                .map(restaurant -> RestaurantMapper.INSTANCE.mapRestauranttoRestaurantDTO(restaurant))
                .collect(Collectors.toList());
        return restaurantDTOList;

    }

    public RestaurantDTO savedRestaurantTnDB(RestaurantDTO restaurant) {

        Restaurant savedRestaurant = restaurantRepository
                .save(RestaurantMapper.INSTANCE.mapRestaurantDTOtoRestaurant(restaurant));
        return RestaurantMapper.INSTANCE.mapRestauranttoRestaurantDTO(savedRestaurant);
    }

    public ResponseEntity<RestaurantDTO> fetchRestaurantByID(Integer id) {

        Optional<Restaurant> resOptional = restaurantRepository.findById(id);
        if (resOptional.isPresent()) {
            return new ResponseEntity<>(RestaurantMapper.INSTANCE.mapRestauranttoRestaurantDTO(resOptional.get()),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

}
