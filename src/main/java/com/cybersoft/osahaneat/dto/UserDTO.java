package com.cybersoft.osahaneat.dto;

import com.cybersoft.osahaneat.entity.Orders;
import com.cybersoft.osahaneat.entity.RatingFood;
import com.cybersoft.osahaneat.entity.RatingRestaurant;
import com.cybersoft.osahaneat.entity.Roles;
import jakarta.persistence.*;

import java.util.Set;

public class UserDTO {

    private int id;
    private String userName;
    private String password;
    private String fullName;
    private String createDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
