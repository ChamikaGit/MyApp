package com.rdmns24.chamiapps.rdmns24live.Models;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NotificationPost implements Serializable
{

    @SerializedName("message")
    @Expose
    private String message;
    private final static long serialVersionUID = -83208719627503222L;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
