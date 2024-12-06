package com.cybersoft.osahaneat.controller;

import com.cybersoft.osahaneat.payload.ResponseData;
import com.cybersoft.osahaneat.payload.SignUpRequest;
import com.cybersoft.osahaneat.service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/kafka")
public class KafkaController {

    @Autowired
    KafkaService kafkaService;
    @PostMapping("/kafka")
    public ResponseEntity<?> kafka(){
        ResponseData responseData = new ResponseData();
        kafkaService.sendMessage("1243");
        return  new ResponseEntity<>(responseData, HttpStatus.OK);
    }
}
