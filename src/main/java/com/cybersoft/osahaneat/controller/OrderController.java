package com.cybersoft.osahaneat.controller;


import com.cybersoft.osahaneat.payload.OrderRequest;
import com.cybersoft.osahaneat.payload.ResponseData;
import com.cybersoft.osahaneat.service.imp.OrderServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderServiceImp orderServiceImp;
    @PostMapping("/orderrestaurant")
    public ResponseEntity<?> orderRestaurant(@RequestBody OrderRequest orderRequest){
        ResponseData responseData = new ResponseData();
        responseData.setData(orderServiceImp.insertOrder(orderRequest));

        return  new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
