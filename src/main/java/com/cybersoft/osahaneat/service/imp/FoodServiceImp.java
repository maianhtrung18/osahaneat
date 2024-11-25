package com.cybersoft.osahaneat.service.imp;

import com.cybersoft.osahaneat.dto.FoodDTO;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FoodServiceImp {
public List<FoodDTO> getFood(int number);

public boolean addFood( String title,
                        MultipartFile file,
                        String timeShip,
                        double price,
                        int cateId);
}
