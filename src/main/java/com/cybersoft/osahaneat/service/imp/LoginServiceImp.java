package com.cybersoft.osahaneat.service.imp;
import com.cybersoft.osahaneat.dto.UserDTO;
import com.cybersoft.osahaneat.payload.SignUpRequest;

import java.util.List;

public interface LoginServiceImp {
    List<UserDTO> getAllUser();
    boolean checkLogin(String userName, String password);
    boolean addUser(SignUpRequest signUpRequest);
}
