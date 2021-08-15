package com.sampson.terceirokotlin

import Model.Employee
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fragment_create.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.concurrent.fixedRateTimer

class CreateFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_create, container, false)
        val txtName = view.findViewById<TextView>(R.id.txtCreateName)
        val txtEmail = view.findViewById<TextView>(R.id.txtCreateEmail)
        val txtJobTitle = view.findViewById<TextView>(R.id.txtCreateJobTitle)
        val txtPhone = view.findViewById<TextView>(R.id.txtCreatePhone)
        val btnSubmit = view.findViewById<ImageButton>(R.id.imgButtonSubmitCreateEmployee)
        val btnReturn = view.findViewById<ImageButton>(R.id.imgButtonReturnCreateEmployee)

        btnSubmit.setOnClickListener {
            if (txtName.length() == 0) {
                txtName.error = "Empty Field"
            }
            if ((txtEmail.length() == 0)) {
                txtEmail.error = "Empty Field"
            }
            if (txtJobTitle.length() == 0) {
                txtJobTitle.error = "Empty Field"
            }
            if (txtPhone.length() == 0) {
                txtPhone.error = "Empty Field"
            }
            if (txtName.length() != 0 && txtEmail.length() != 0 && txtJobTitle.length() != 0 && txtPhone.length() != 0) {
                val url = "https://bootdey.com/img/Content/avatar/avatar1.png"
                val employee = Employee(0,txtName.text.toString(),txtEmail.text.toString(),txtJobTitle.text.toString(),txtPhone.text.toString(),url,"")
                Toast.makeText(context, "Submitting", Toast.LENGTH_SHORT).show()

                val gson = GsonBuilder().create()
                val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson)).build()
                val apiService = retrofit.create(ApiService::class.java)
                apiService.addEmployee(employee).enqueue(object : Callback<Employee> {
                    override fun onResponse(call: Call<Employee>, response: Response<Employee>) {
                        Log.d(TAG,"Success")
                        Toast.makeText(context, "Employee Added Successfully", Toast.LENGTH_SHORT).show()
                        txtName.text = ""
                        txtEmail.text = ""
                        txtJobTitle.text = ""
                        txtPhone.text = ""
                        txtName.requestFocus()
                    }

                    override fun onFailure(call: Call<Employee>, t: Throwable) {
                        Log.d(TAG,"Error")
                    }
                })
            }
        }

        btnReturn.setOnClickListener {
            val fragment = Fragment(R.layout.fragment_home)
            fragmentManager?.beginTransaction()?.replace(R.id.nav_host_fragment, fragment)?.commit()
        }

        return view
    }

    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

}



