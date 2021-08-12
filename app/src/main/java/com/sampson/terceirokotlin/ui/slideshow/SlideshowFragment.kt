package com.sampson.terceirokotlin.ui.slideshow

import Control.EmployeeAdapter
import Control.EmployeeAdapterListView
import Model.Employee
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.*
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
private const val TAG = "SlideShowFragment"

class SlideshowFragment : Fragment() {

    var allEmployees = listOf<Employee>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {

        val view = inflater.inflate(R.layout.fragment_slideshow, container, false)
        val rvEmployees = view.findViewById<RecyclerView>(R.id.rvListEmployees)
        var employeeAdapter = EmployeeAdapter(container!!.context)
        var employeeAdapterListView = EmployeeAdapterListView(container!!.context)

        val pbCircularBar = view.findViewById<ProgressBar>(R.id.pbCircularBar)
        val imgButtonRefresh = view.findViewById<ImageButton>(R.id.imgButtonRefresh)
        val imgButtonCardView = view.findViewById<ImageButton>(R.id.imgButtonCardview)
        val imgButtonListView = view.findViewById<ImageButton>(R.id.imgButtonListView)
        val imgButtonSortList = view.findViewById<ImageButton>(R.id.imgButtonSort)
        val txtNameSearch = view.findViewById<EditText>(R.id.edtSearchEmployee)

        rvEmployees.layoutManager = LinearLayoutManager(context)
        rvEmployees.adapter = employeeAdapterListView
        pbCircularBar.visibility = View.VISIBLE
        retrieveInformationListView(employeeAdapterListView)

        imgButtonRefresh.setOnClickListener {
            rvEmployees.adapter = employeeAdapterListView
            Toast.makeText(context, "Refresh", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "Refresh")
            retrieveInformationCardView(employeeAdapter)
        }

        imgButtonCardView.setOnClickListener {
            rvEmployees.adapter = employeeAdapter
            Toast.makeText(context, "Cardview", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "Cardview")
            retrieveInformationCardView(employeeAdapter)
        }

        imgButtonListView.setOnClickListener {
            rvEmployees.adapter = employeeAdapterListView
            Toast.makeText(context, "Listview", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "Listview")
            retrieveInformationListView(employeeAdapterListView)
        }

        imgButtonSortList.setOnClickListener {
            if (rvEmployees.adapter is EmployeeAdapterListView){
                employeeAdapterListView.sortByName()
            } else {
                employeeAdapter.sortByName()
            }
        }

        txtNameSearch.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE){
                if (txtNameSearch.length() == 0) {
                    txtNameSearch.error = "Empty Name"
                } else {
                    if (rvEmployees.adapter is EmployeeAdapterListView){
                        employeeAdapterListView.searchByName(txtNameSearch.text.toString())

                    } else {
                        employeeAdapter.searchByName(txtNameSearch.text.toString())
                    }
                }
                true
            } else{
                false
            }
        }

        return view
    }

    private fun retrieveInformationCardView(employeeAdapter: EmployeeAdapter) {
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
                employeeAdapter.setEmployeeList(response.body()!!)
                if (response.body() == null) {
                    Log.i(TAG, "Did not receive a valid response body")
                    return
                }
                pbCircularBar.visibility = View.GONE
            }

            override fun onFailure(call: Call<List<Employee>>, t: Throwable) {
                Log.i(TAG, "onFailure $t")
                Toast.makeText(context, "Timeout", Toast.LENGTH_SHORT).show()
                pbCircularBar.visibility = View.GONE
            }
        })

    }

    private fun retrieveInformationListView(employeeAdapterListView: EmployeeAdapterListView) {
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
                employeeAdapterListView.setEmployeeList(response.body()!!)
                if (response.body() == null) {
                    Log.i(TAG, "Did not receive a valid response body")
                    return
                }
                pbCircularBar.visibility = View.GONE
            }
            override fun onFailure(call: Call<List<Employee>>, t: Throwable) {
                Log.i(TAG, "onFailure $t")
                Toast.makeText(context, "Timeout", Toast.LENGTH_SHORT).show()
                pbCircularBar.visibility = View.GONE
            }
        })

    }

}