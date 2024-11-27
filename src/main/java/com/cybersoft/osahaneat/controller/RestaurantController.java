package com.cybersoft.osahaneat.controller;

import com.cybersoft.osahaneat.dto.RestaurantDTO;
import com.cybersoft.osahaneat.entity.Restaurant;
import com.cybersoft.osahaneat.payload.ResponseData;
import com.cybersoft.osahaneat.service.imp.FileServiceImp;
import com.cybersoft.osahaneat.service.imp.RestaurantServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {
    @Autowired
    FileServiceImp fileServiceImp;
    @Autowired
    RestaurantServiceImp restaurantServiceImp;
    @PostMapping("/add")
    public ResponseEntity<?> createRestaurant(@RequestParam MultipartFile file,
                                              @RequestParam String title ,
                                              @RequestParam String subtitle ,
                                              @RequestParam String description ,
                                              @RequestParam boolean isFreeship ,
                                              @RequestParam String address
    ){
        ResponseData responseData= new ResponseData();
        boolean isSuccess =  restaurantServiceImp.insertRestaurant(file, title, subtitle, description, isFreeship, address);
        responseData.setData(isSuccess);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/gettoprestaurant/{number}")
    public ResponseEntity<?> getTopRestaurant(@PathVariable int number){
        ResponseData responseData= new ResponseData();
        List<RestaurantDTO> listRestaurant = restaurantServiceImp.topRestaurant(number);
        responseData.setData(listRestaurant);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/file/{fileName}")
    public ResponseEntity<?> getFileRestaurant(@PathVariable String fileName){
        Resource resource = fileServiceImp.loadFile(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, resource.getFilename()).body(resource);
    }
    @Cacheable(value = "getDetailRestaurant", key = "#idRestaurant")
    @GetMapping("/detail")
    public ResponseEntity<?> getDetailRestaurant(@RequestParam int idRestaurant){
        ResponseData responseData= new ResponseData();
        RestaurantDTO restaurantDTO  =  restaurantServiceImp.getDetailRestaurant(idRestaurant);
        responseData.setData(restaurantDTO);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }


    @Autowired
    private CacheManager cacheManager;

    @GetMapping("/clear-cache")
    @CacheEvict(value = "getDetailRestaurant", allEntries = true)
    public void clearAllCache() {
        // Logic khác nếu cần
    }
    @GetMapping("/check-cache")
    public Object checkCache(@RequestParam String cacheName,@RequestParam int key) {
        Cache cache = cacheManager.getCache(cacheName);
        if (cache != null) {
            return cache.get(key) != null ? cache.get(key).get() : "Cache không có dữ liệu!";
        }
        return "Cache không tồn tại!";
    }


}
