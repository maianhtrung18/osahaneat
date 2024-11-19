package com.cybersoft.osahaneat.service;

import com.cybersoft.osahaneat.dto.UserDTO;
import com.cybersoft.osahaneat.entity.Users;
import com.cybersoft.osahaneat.repository.UserRepository;
import com.cybersoft.osahaneat.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserServiceImp {
    @Autowired
    UserRepository  userRepository;

    @Override
    public List<UserDTO> getAllUser() {
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
}
