package com.cybersoft.osahaneat.testData;

import com.cybersoft.osahaneat.entity.Users;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class UsersRepositoryMockData {
    public int id = 567;
    public String userName  = "Nguyen Van A";
    public String password = "123456789";
    public String fullName = "Nguyen Van Anh";
    public String createDate = "22/09/2025";
}
