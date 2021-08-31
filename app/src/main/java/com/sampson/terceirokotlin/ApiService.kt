package com.sampson.terceirokotlin

import Model.Employee
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("employees/all")
    fun getAllEmployees() : Call<List<Employee>>

    @POST("employees/add")
    fun addEmployee(@Body employee: Employee) : Call<Employee>

    @DELETE("employees/delete/{id}")
    fun removeEmployee(@Path("id") employeeInt: Int) : Call<Void>
}