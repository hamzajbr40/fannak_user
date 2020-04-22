package com.hamzajbr.fannak_user.models.requests;

import com.google.gson.annotations.SerializedName;

public class SignupRequest {
    @SerializedName("email")
    public String email;
    @SerializedName("name")
    public String name;
    @SerializedName("password")
    public String password;
}
