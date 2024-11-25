package com.cybersoft.osahaneat.controller;

import com.cybersoft.osahaneat.dto.FoodDTO;
import com.cybersoft.osahaneat.payload.ResponseData;
import com.cybersoft.osahaneat.service.imp.CategoryServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    CategoryServiceImp categoryServiceImp;
    @GetMapping("/getfoodscategory")
     public ResponseEntity<?> getfoodCaterogy(){
        ResponseData responseData = new ResponseData();
        responseData.setData(categoryServiceImp.getAllCategory());
        return new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
