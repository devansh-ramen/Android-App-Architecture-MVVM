package com.devanshramen.loginapplication.repository.remote;

import com.devanshramen.loginapplication.repository.model.LoginRequest;
import com.devanshramen.loginapplication.repository.model.LoginResponse;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by devanshramen on 11/19/17.
 */

public interface APIService {

    String BASE_URL = "http://demo6183250.mockable.io/";    // mocked login service

    @POST("login")   // mocked login service
    Observable<LoginResponse> login(@Body LoginRequest request);
}
