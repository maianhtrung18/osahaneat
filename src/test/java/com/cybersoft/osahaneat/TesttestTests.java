package com.cybersoft.osahaneat;

import com.cybersoft.osahaneat.dto.UserDTO;
import com.cybersoft.osahaneat.entity.Users;
import com.cybersoft.osahaneat.repository.UserRepository;
import com.cybersoft.osahaneat.service.imp.UserServiceImp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
public class TesttestTests {
    @Autowired
    private UserServiceImp userServiceImp;
    @MockBean
    private UserRepository userRepository;


    @Test
    void test(){
        Users user1 = new Users();
        user1.setId(1);
        user1.setUserName("14553");
        List<Users> mockUsers = Arrays.asList(
            user1
        );
        when(userRepository.findAll()).thenReturn(mockUsers);
        List<UserDTO> result = userServiceImp.getAllUser();
        System.out.println(result.get(0).getUserName());
        System.out.println(result.get(0).getId());
        System.out.println("*********");
    }

    @Test
    void test1(){
        Users user1 = new Users();
        user1.setId(1);
        user1.setUserName("14553");
        List<Users> mockUsers = Arrays.asList(
        );
        when(userRepository.findAll()).thenReturn(mockUsers);
        List<UserDTO> result = userServiceImp.getAllUser();
        System.out.println(result.size());

    }


}
