package com.sampson.terceirokotlin.ui.slideshow

import Control.EmployeeAdapter
import Model.Employee
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import com.sampson.terceirokotlin.ApiService
import com.sampson.terceirokotlin.R
import kotlinx.android.synthetic.main.fragment_slideshow.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://ruy-manager-employee.herokuapp.com/"
private const val TAG =  "SlideShowFragment"

class SlideshowFragment : Fragment() {


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.fragment_slideshow, container, false)

        val rvEmployees = view.findViewById<RecyclerView>(R.id.rvListEmployees)
        var employeeAdapter = EmployeeAdapter(container!!.context)
        rvEmployees.layoutManager = LinearLayoutManager(context)
        rvEmployees.adapter = employeeAdapter

        val pbCircularBar = view.findViewById<ProgressBar>(R.id.pbCircularBar)
        pbCircularBar.visibility = View.VISIBLE


        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
            GsonConverterFactory.create(gson)).build()
        val apiService = retrofit.create(ApiService::class.java)
        apiService.getAllEmployees().enqueue(object : Callback<List<Employee>> {
            override fun onResponse(
                call: Call<List<Employee>>,
                response: Response<List<Employee>>
            ) {
                Log.i(TAG, "onResponse $response")
                employeeAdapter.setEmployeeList(response.body()!!)
                if (response.body() == null) {
                    Log.i(TAG, "Did not receive a valid response body")
                    return
                }
                pbCircularBar.visibility = View.GONE
            }
            override fun onFailure(call: Call<List<Employee>>, t: Throwable) {
                Log.i(TAG, "onFailure $t")
            }
        })

        return view
    }
}