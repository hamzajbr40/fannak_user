package com.hamzajbr.fannak_user.models.responses;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {
    @SerializedName("Name")
    public String name;
    @SerializedName("id")
    public int id;
}
