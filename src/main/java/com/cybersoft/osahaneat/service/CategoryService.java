package com.cybersoft.osahaneat.service;

import com.cybersoft.osahaneat.dto.CaterogyDTO;
import com.cybersoft.osahaneat.dto.FoodDTO;
import com.cybersoft.osahaneat.entity.Category;
import com.cybersoft.osahaneat.entity.Food;
import com.cybersoft.osahaneat.repository.CategoryRepository;
import com.cybersoft.osahaneat.service.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService implements CategoryServiceImp {

    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public List<CaterogyDTO> getAllCategory() {
        List<CaterogyDTO> listCategory = new ArrayList<>();
        List<Category> categoriesResponse = categoryRepository.findAll();
        for(Category category: categoriesResponse){
            CaterogyDTO caterogyDTO = new CaterogyDTO();
            List<FoodDTO> foodDTOList = new ArrayList<>();
            caterogyDTO.setId(category.getId());
            caterogyDTO.setCateName(category.getNameCate());
            caterogyDTO.setCreeateDate(category.getCreateDate());
            for(Food food: category.getListFood()){
                FoodDTO foodDTO  = new FoodDTO();
                foodDTO.setId(food.getId());
                foodDTO.setTitle(food.getTitle());
                foodDTO.setImage(food.getImage());
                foodDTO.setTimeShip(food.getTimeShip());
                foodDTO.setPrice(food.getPrice());
                foodDTO.setCategory(category.getNameCate());
                foodDTOList.add(foodDTO);
            }
            caterogyDTO.setListFood(foodDTOList);
            listCategory.add(caterogyDTO);
        }
        return listCategory;
    }
}
