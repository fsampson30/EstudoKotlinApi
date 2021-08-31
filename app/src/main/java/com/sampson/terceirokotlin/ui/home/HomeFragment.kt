package com.sampson.terceirokotlin.ui.home

import Model.Employee
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.gson.GsonBuilder
import com.sampson.terceirokotlin.ApiService
import com.sampson.terceirokotlin.R
import kotlinx.android.synthetic.main.fragment_slideshow.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

const val BASE_URL = "https://ruy-manager-employee.herokuapp.com/"
internal const val TAG = "HomeFragment"

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val txtTotalEmployees = view.findViewById<TextView>(R.id.txtHomeFregmentQtdEmployees)
        val txtTotalCareers = view.findViewById<TextView>(R.id.txtHomeFragmentQtdCareers)
        val txtLastSync = view.findViewById<TextView>(R.id.txtHomeFragmentLastSync)
        val progressBar = view.findViewById<ProgressBar>(R.id.progressBarHomeFregment)
        progressBar.visibility = View.VISIBLE

        val simpleDate = SimpleDateFormat("dd/M/yyyy hh:mm:ss")
        val currentDate = simpleDate.format(Date())

        val gson = GsonBuilder().create()
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
            GsonConverterFactory.create(gson)
        ).build()
        val apiService = retrofit.create(ApiService::class.java)
        apiService.getAllEmployees().enqueue(object : Callback<List<Employee>> {
            override fun onResponse(
                call: Call<List<Employee>>,
                response: Response<List<Employee>>
            ) {
                Log.i(TAG, "onResponse $response")
                val list = response.body()
                val listCareers = list!!.groupBy { it.jobTitle }
                txtTotalEmployees.text = list.size.toString()
                txtTotalCareers.text = listCareers.size.toString()
                txtLastSync.text = "${getString(R.string.last_sync)} $currentDate"

                Log.d(TAG, listCareers.toString())
                if (response.body() == null) {
                    Log.i(TAG, "Did not receive a valid response body")
                    return
                }
                progressBar.visibility = View.GONE
            }

            override fun onFailure(call: Call<List<Employee>>, t: Throwable) {
                Log.i(TAG, "onFailure $t")
                Toast.makeText(context, "Timeout", Toast.LENGTH_SHORT).show()
                progressBar.visibility = View.GONE
            }
        })

        return view
    }
}