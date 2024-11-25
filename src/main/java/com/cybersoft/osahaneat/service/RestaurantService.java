package com.cybersoft.osahaneat.service;

import com.cybersoft.osahaneat.dto.CaterogyDTO;
import com.cybersoft.osahaneat.dto.FoodDTO;
import com.cybersoft.osahaneat.dto.RestaurantDTO;
import com.cybersoft.osahaneat.entity.Food;
import com.cybersoft.osahaneat.entity.MenuRestaurant;
import com.cybersoft.osahaneat.entity.RatingRestaurant;
import com.cybersoft.osahaneat.entity.Restaurant;
import com.cybersoft.osahaneat.repository.RestaurantRepository;
import com.cybersoft.osahaneat.service.imp.FileServiceImp;
import com.cybersoft.osahaneat.service.imp.RestaurantServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class RestaurantService implements RestaurantServiceImp {
    @Autowired
    FileServiceImp fileServiceImp;
    @Autowired
    RestaurantRepository restaurantRepository;
    @Override
    public boolean insertRestaurant(MultipartFile file, String title, String subtitle, String description, boolean isFreeship, String address) {
        boolean isInsertSuccess = false;
        try{
            boolean isSaveFileSuccess = fileServiceImp.saveFile(file);
            if(isSaveFileSuccess){
                Restaurant restaurant = new Restaurant();
                restaurant.setTitle(title);
                restaurant.setSubtitle(subtitle);
                restaurant.setDescription(description);
                restaurant.setImage(file.getOriginalFilename());
                restaurant.setFreeShip(isFreeship);
                restaurant.setAddress(address);
                Date date = new Date();
                restaurant.setOpenDate(date);
                restaurantRepository.save(restaurant);
                isInsertSuccess =true;
            }
        }catch (Exception e){
            System.out.println("Insert restaurant failed: " + e);

        }
        return isInsertSuccess;
    }

    @Override
    public List<RestaurantDTO> topRestaurant(int number) {
        List<RestaurantDTO> listRestaurantDTO = new ArrayList<>();
        Pageable pageable = PageRequest.of(0, number);
        try {
            List<Restaurant> listRestaurant = restaurantRepository.findAllByOrderByIsFreeShipDesc(pageable).getContent();
            for(Restaurant restaurant: listRestaurant){
                RestaurantDTO restaurantDTO = new RestaurantDTO();
                restaurantDTO.setId(restaurant.getId());
                restaurantDTO.setTitle(restaurant.getTitle());
                restaurantDTO.setSubTitle(restaurant.getSubtitle());
                restaurantDTO.setDescription(restaurant.getDescription());
                restaurantDTO.setImage(restaurant.getImage());
                restaurantDTO.setFreeship(restaurant.isFreeShip());
                restaurantDTO.setAddress(restaurant.getAddress());
                restaurantDTO.setOpenDate(restaurant.getOpenDate());
                restaurantDTO.setRating(calculateRestaurantRating(restaurant.getListRatingRestaurant()));
                listRestaurantDTO.add(restaurantDTO);
            }
        }catch (Exception e){
            System.out.println("Can not get list Restaurant" + e);
        }
        return listRestaurantDTO;
    }

    public double calculateRestaurantRating(Set<RatingRestaurant> ratingRestaurantList){
        double ratingSum = 0;
        for (RatingRestaurant ratingRestaurant: ratingRestaurantList){
            ratingSum += ratingRestaurant.getRatePoint();
        }
        return ratingSum/ratingRestaurantList.size();

    }

    @Override
    public RestaurantDTO getDetailRestaurant(int restaurantId) {
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        try{
            Optional<Restaurant> restaurantResponse = restaurantRepository.findById(restaurantId);
            if (restaurantResponse.isPresent()){
                List<CaterogyDTO> caterogyDTOList = new ArrayList<>();
                restaurantDTO.setId(restaurantResponse.get().getId());
                restaurantDTO.setTitle(restaurantResponse.get().getTitle());
                restaurantDTO.setSubTitle(restaurantResponse.get().getSubtitle());
                restaurantDTO.setDescription(restaurantResponse.get().getDescription());
                restaurantDTO.setImage(restaurantResponse.get().getImage());
                restaurantDTO.setRating(calculateRestaurantRating(restaurantResponse.get().getListRatingRestaurant()));
                restaurantDTO.setFreeship(restaurantResponse.get().isFreeShip());
                restaurantDTO.setAddress(restaurantResponse.get().getAddress());
                restaurantDTO.setOpenDate(restaurantResponse.get().getOpenDate());
                for(MenuRestaurant menuRestaurant: restaurantResponse.get().getListMenuRestaurant()){
                    CaterogyDTO caterogyDTO = new CaterogyDTO();
                    caterogyDTO.setCateName(menuRestaurant.getCategory().getNameCate());
                    caterogyDTO.setCreeateDate(menuRestaurant.getCategory().getCreateDate());
                    List<FoodDTO> foodDTOList = new ArrayList<>();
                    for (Food food: menuRestaurant.getCategory().getListFood()){
                        FoodDTO foodDTO = new FoodDTO();
                        foodDTO.setId(food.getId());
                        foodDTO.setPrice(food.getPrice());
                        foodDTO.setTitle(food.getTitle());
                        foodDTO.setCategory(menuRestaurant.getCategory().getNameCate());
                        foodDTO.setImage(food.getImage());
                        foodDTO.setTimeShip(food.getTimeShip());
                        foodDTOList.add(foodDTO);
                    }
                    caterogyDTO.setListFood(foodDTOList);
                    caterogyDTOList.add(caterogyDTO);
                }
                restaurantDTO.setCaterogyDTOList(caterogyDTOList);
            }
        }
        catch (Exception e){
            System.out.println("Khong lay duoc Restaurant" + e);
        }
        return restaurantDTO;
    }
}
