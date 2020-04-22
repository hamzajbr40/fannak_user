package com.hamzajbr.fannak_user.models.requests;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("email")
    public String email;
    @SerializedName("password")
    public String password;
}
