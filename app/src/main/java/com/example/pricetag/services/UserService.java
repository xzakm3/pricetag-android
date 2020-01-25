package com.example.pricetag.services;

import com.example.pricetag.data.requests.user.LoginRequest;
import com.example.pricetag.data.requests.user.LoginResponse;
import com.example.pricetag.data.requests.user.SignupRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {

    @POST("/signup")
    Call<Void> signup(@Body SignupRequest signupRequest);
    
    @POST("/auth/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

}
