package com.cybersoft.osahaneat.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class CaterogyDTO {
    private int  id;
    private String cateName;
    private Date creeateDate;
    private List<FoodDTO> listFood;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public Date getCreeateDate() {
        return creeateDate;
    }

    public void setCreeateDate(Date creeateDate) {
        this.creeateDate = creeateDate;
    }

    public List<FoodDTO> getListFood() {
        return listFood;
    }

    public void setListFood(List<FoodDTO> listFood) {
        this.listFood = listFood;
    }
}
