package com.devanshramen.loginapplication.db.remote;


import com.devanshramen.loginapplication.model.LoginRequest;
import com.devanshramen.loginapplication.model.LoginResponse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by devanshramen on 11/19/17.
 */

public interface APIService {

    String BASE_URL = "http://demo6183250.mockable.io/";    // mocked login service

    @POST("login")   // mocked login service
    Observable<LoginResponse> login(@Body LoginRequest request);
}
