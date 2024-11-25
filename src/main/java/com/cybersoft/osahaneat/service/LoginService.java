package com.cybersoft.osahaneat.service;

import com.cybersoft.osahaneat.dto.UserDTO;
import com.cybersoft.osahaneat.entity.Roles;
import com.cybersoft.osahaneat.entity.Users;
import com.cybersoft.osahaneat.payload.SignUpRequest;
import com.cybersoft.osahaneat.repository.UserRepository;
import com.cybersoft.osahaneat.service.imp.LoginServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService implements LoginServiceImp {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
@Override
public List<UserDTO> getAllUser(){
    List<Users> listUser = userRepository.findAll();
    List<UserDTO> userDTOList = new ArrayList<>();
    for (Users user: listUser){
        UserDTO userDto = new UserDTO();
        userDto.setId(user.getId());
        userDto.setUserName(user.getUserName());
        userDto.setPassword(user.getPassword());
        userDto.setFullName(user.getFullName());
        userDto.setCreateDate(user.getCreateDate());
        userDTOList.add(userDto);
    }
    return userDTOList;
}
@Override
public boolean checkLogin(String userName, String password) {
Users user = userRepository.findByUserName(userName);
  return passwordEncoder.matches(password, user.getPassword());
}

@Override
public boolean addUser(SignUpRequest signUpRequest) {
    Roles roles = new Roles();
    roles.setId(signUpRequest.getRoleId());
    Users users = new Users();
    users.setFullName(signUpRequest.getFullName());
    users.setUserName(signUpRequest.getUserName());
    users.setPassword(signUpRequest.getPassword());
    users.setRoles(roles);
    try {
        userRepository.save(users);
        return true;
    }catch (Exception e){
        return false;
    }

}
}
