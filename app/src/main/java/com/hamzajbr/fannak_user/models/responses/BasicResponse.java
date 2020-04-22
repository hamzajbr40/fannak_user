package com.hamzajbr.fannak_user.models.responses;

import com.google.gson.annotations.SerializedName;

public class BasicResponse {
    @SerializedName("code")
    public int code;
    @SerializedName("message")
    public String message;
    @SerializedName("executionSuccessful")
    public boolean executionSuccessful;

}
