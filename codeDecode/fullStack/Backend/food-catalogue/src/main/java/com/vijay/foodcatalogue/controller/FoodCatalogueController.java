package com.vijay.foodcatalogue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vijay.foodcatalogue.dto.FoodCataloguePage;
import com.vijay.foodcatalogue.dto.FoodItemDTO;
import com.vijay.foodcatalogue.service.FoodCatalogueService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/foodCatalogue")
public class FoodCatalogueController {

    @Autowired
    FoodCatalogueService foodCatalogueService;

    @PostMapping("addFoodItem")
    public ResponseEntity<FoodItemDTO> addFoodItem(@RequestBody FoodItemDTO foodItemDTO) {

        FoodItemDTO foodItemToSave = foodCatalogueService.addFoodItem(foodItemDTO);
        return new ResponseEntity<>(foodItemToSave, HttpStatus.CREATED);
    }

    @GetMapping("/fetchRestaurantAndFoodItemsById/{restaurantId}")
    public ResponseEntity<FoodCataloguePage> fetchRestaurantDetailsWithFoodMenu(@PathVariable Integer restaurantId) {

        FoodCataloguePage foodCataloguePage = foodCatalogueService.fetchFoodCataloguePageDetail(restaurantId);
        return new ResponseEntity<>(foodCataloguePage, HttpStatus.OK);
    }

}
