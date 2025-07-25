package com.example.myassssmentapplication.data.api

import com.example.myassssmentapplication.data.model.DashboardResponse
import com.example.myassssmentapplication.data.model.LoginRequest
import com.example.myassssmentapplication.data.model.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST("sydney/auth") // make sure this is your location
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET("dashboard/{keypass}")
    fun getDashboardData(@Path("keypass") keypass: String): Call<DashboardResponse>
}
