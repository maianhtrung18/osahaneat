package com.cybersoft.osahaneat.service.imp;

import com.cybersoft.osahaneat.dto.RestaurantDTO;
import com.cybersoft.osahaneat.entity.Restaurant;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RestaurantServiceImp {
    boolean insertRestaurant( MultipartFile file,
                              String title ,
                              String subtitle ,
                              String description ,
                              boolean isFreeship ,
                              String address);
    List<RestaurantDTO> topRestaurant(int number);
    RestaurantDTO getDetailRestaurant(int restaurantId);
}
