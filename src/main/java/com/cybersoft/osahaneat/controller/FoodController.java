package com.cybersoft.osahaneat.controller;

import com.cybersoft.osahaneat.dto.FoodDTO;
import com.cybersoft.osahaneat.payload.ResponseData;
import com.cybersoft.osahaneat.service.imp.FileServiceImp;
import com.cybersoft.osahaneat.service.imp.FoodServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/food")
public class FoodController {

    @Autowired
    FoodServiceImp foodServiceImp;

    @Autowired
    FileServiceImp fileServiceImp;
    @GetMapping("/getfoods/{number}")
    public ResponseEntity<?> getFood(@PathVariable int number){
        ResponseData responseData = new ResponseData();
        List<FoodDTO> listFood = foodServiceImp.getFood(number);
        responseData.setData(listFood);
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/addfood")
    public ResponseEntity<?> addFood(@RequestParam String title,
                                     @RequestParam MultipartFile file,
                                     @RequestParam String timeShip,
                                     @RequestParam double price,
                                     @RequestParam int cateId
                                     ){
    ResponseData responseData = new ResponseData();
    boolean isAdd = foodServiceImp.addFood(title,file,timeShip,price,cateId);
    responseData.setData(isAdd);
    return new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @GetMapping("/file/{fileName}")
    public ResponseEntity<?> getFileRestaurant(@PathVariable String fileName){
        Resource resource = fileServiceImp.loadFile(fileName);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, resource.getFilename()).body(resource);
    }
}
