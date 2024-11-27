package com.cybersoft.osahaneat.controller;

import com.cybersoft.osahaneat.payload.ResponseData;
import com.cybersoft.osahaneat.payload.SignUpRequest;
import com.cybersoft.osahaneat.service.LoginService;
import com.cybersoft.osahaneat.service.imp.LoginServiceImp;
import com.cybersoft.osahaneat.utils.JwtUtilHelper;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.Base64;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    LoginService loginServiceImp;

    @Autowired
    JwtUtilHelper jwtUtilHelper;

    Logger logger = LoggerFactory.getLogger(this.getClass());
    @CrossOrigin("*")
    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestParam String username, @RequestParam String password){
        ResponseData responseData = new ResponseData();
//        SecretKey  secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//        String encryptKey = Encoders.BASE64URL.encode(secretKey.getEncoded());
//        System.out.println(encryptKey);
        logger.error("hhhg");

        if(loginServiceImp.checkLogin(username, password)){
            String token = jwtUtilHelper.gennerateToken(username);
            responseData.setData(token);

        }
        else {
            responseData.setData("");
            responseData.setSuccess(false);
        }

    return  new ResponseEntity<>(responseData, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest){
        ResponseData responseData = new ResponseData();
        responseData.setData(loginServiceImp.addUser(signUpRequest));

        return  new ResponseEntity<>(responseData, HttpStatus.OK);
    }

}
