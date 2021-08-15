package com.sampson.terceirokotlin

import Model.Employee
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @GET("employees/all")
    fun getAllEmployees() : Call<List<Employee>>

    @POST("employees/add")
    fun addEmployee(@Body employee: Employee) : Call<Employee>
}