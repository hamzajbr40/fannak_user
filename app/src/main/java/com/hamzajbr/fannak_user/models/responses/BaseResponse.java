package com.hamzajbr.fannak_user.models.responses;

import com.google.gson.annotations.SerializedName;

public class BaseResponse<T> extends BasicResponse{
    @SerializedName("data")
    public T data;
}
