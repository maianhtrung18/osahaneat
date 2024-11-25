package com.cybersoft.osahaneat.service;

import com.cybersoft.osahaneat.dto.FoodDTO;
import com.cybersoft.osahaneat.entity.Category;
import com.cybersoft.osahaneat.entity.Food;
import com.cybersoft.osahaneat.repository.FoodRepository;
import com.cybersoft.osahaneat.service.imp.FileServiceImp;
import com.cybersoft.osahaneat.service.imp.FoodServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

@Service
public class FoodService implements FoodServiceImp {
    @Autowired
    FoodRepository foodRepository;

    @Autowired
    FileServiceImp fileServiceImp;
    public List<FoodDTO> getFood(int number){
        List<FoodDTO> foodList = new ArrayList<>();
        Pageable pageable = PageRequest.of(0, number);
        List<Food> foodListResponse = foodRepository.findAll(pageable).getContent();
        for(Food food: foodListResponse){
            FoodDTO foodDTO = new FoodDTO();
            foodDTO.setId(food.getId());
            foodDTO.setTitle(food.getTitle());
            foodDTO.setTimeShip(food.getTimeShip());
            foodDTO.setPrice(food.getPrice());
            foodDTO.setImage(food.getImage());
            foodDTO.setCategory(food.getCategory().getNameCate());
            foodList.add(foodDTO);
        }

    return foodList;
    }

    @Override
    public boolean addFood(String title, MultipartFile file, String timeShip, double price, int cateId) {
        boolean isSuccess = false;
        try{
            if(fileServiceImp.saveFile(file)){
                Food food = new Food();
                Category category = new Category();
                category.setId(cateId);
                food.setTitle(title);
                food.setImage(file.getOriginalFilename());
                food.setTimeShip(timeShip);
                food.setPrice(price);
                food.setCategory(category);
                foodRepository.save(food);
                isSuccess = true;
            };
        }catch (Exception e){
            System.out.println("Failed to add food: " +  e);
        }
        return isSuccess;
    }
}
