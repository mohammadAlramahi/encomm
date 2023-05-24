package com.example.encomm;


import android.telecom.Call;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Api {

    @Multipart
    @POST("login.php")
    Call<UserModel> login(@Part("Phone") RequestBody phone,
                          @Part("Password") RequestBody password,
                          @Part("ConCode") RequestBody ConCode

    );

    @Multipart
    @POST("SignUp.php")
    Call<UserModel> SignUp(@Part("Name") RequestBody Name,
                           @Part("Phone") RequestBody Number,
                           @Part("Email") RequestBody Email,
                           @Part("Password") RequestBody Password,
                           @Part("ConCode") RequestBody ConCode

    );
}
