package com.vijay.foodcatalogue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.vijay.foodcatalogue.dto.FoodCataloguePage;
import com.vijay.foodcatalogue.dto.FoodItemDTO;
import com.vijay.foodcatalogue.dto.Restaurant;
import com.vijay.foodcatalogue.entity.FoodItem;
import com.vijay.foodcatalogue.mapper.FoodItemMapper;
import com.vijay.foodcatalogue.repository.FoodItemRepository;

@Service
public class FoodCatalogueService {

    @Autowired
    FoodItemRepository foodItemRepository;

    @Autowired
    RestTemplate restTemplate;

    public FoodItemDTO addFoodItem(FoodItemDTO foodItemDTO) {

        FoodItem foodItemSaved = foodItemRepository.save(FoodItemMapper.INSTANCE.mapFoodItemDTOToFoodItem(foodItemDTO));
        return FoodItemMapper.INSTANCE.mapFoodItemToFoodItemDTO(foodItemSaved);

    }

    public FoodCataloguePage fetchFoodCataloguePageDetail(Integer restaurantId) {

        List<FoodItem> foodItemList = fetchFoodItemList(restaurantId);
        Restaurant restaurant = fetchRestaurantDetailsFromRestaurantMS(restaurantId);
        return createFoodCataloguePage(foodItemList, restaurant);

    }

    private FoodCataloguePage createFoodCataloguePage(List<FoodItem> foodItemList, Restaurant restaurant) {

        FoodCataloguePage foodCataloguePage = new FoodCataloguePage();
        foodCataloguePage.setFoodItemList(foodItemList);
        foodCataloguePage.setRestaurant(restaurant);
        return foodCataloguePage;
    }

    private Restaurant fetchRestaurantDetailsFromRestaurantMS(Integer restaurantId) {

        return restTemplate.getForObject("http://RESTAURANT-SERVICE/restaurant/fetchById/" + restaurantId,
                Restaurant.class);
    }

    private List<FoodItem> fetchFoodItemList(Integer restaurantId) {

        return foodItemRepository.findByRestaurantId(restaurantId);
    }

}
