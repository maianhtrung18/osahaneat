package com.cybersoft.osahaneat.dto;

import java.util.Date;
import java.util.List;

public class RestaurantDTO {
    private int id;
    private String title;
    private  String subTitle;
    private  String description;
    private  String image;
    private boolean isFreeship;
    private String address;
    private Date openDate;
    private double rating;

    private List<CaterogyDTO> caterogyDTOList;

    public List<CaterogyDTO> getCaterogyDTOList() {
        return caterogyDTOList;
    }

    public void setCaterogyDTOList(List<CaterogyDTO> caterogyDTOList) {
        this.caterogyDTOList = caterogyDTOList;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isFreeship() {
        return isFreeship;
    }

    public void setFreeship(boolean freeship) {
        isFreeship = freeship;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getOpenDate() {
        return openDate;
    }

    public void setOpenDate(Date openDate) {
        this.openDate = openDate;
    }
}
