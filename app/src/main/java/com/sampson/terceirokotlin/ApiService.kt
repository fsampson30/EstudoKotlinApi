package com.sampson.terceirokotlin

import Model.Employee
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("employees/all")
    fun getAllEmployees() : Call<List<Employee>>
}