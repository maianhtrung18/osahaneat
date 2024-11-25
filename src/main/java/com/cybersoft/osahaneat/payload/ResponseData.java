package com.cybersoft.osahaneat.payload;

import org.springframework.context.annotation.Primary;

/***
 * {
 *     response
 *     data
 *
 * }
 */
public class ResponseData {
    private int status = 200;
    private String description;
    private Object data;

    private boolean isSuccess = true;

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
