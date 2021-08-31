package com.sampson.terceirokotlin

import Model.Employee
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.google.gson.GsonBuilder
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_slideshow.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class EmployeeDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_details)

        val txtId = findViewById<TextView>(R.id.txtIdEmployeeDetails)
        val txtName = findViewById<TextView>(R.id.txtNameEmployeeDetails)
        val txtEmail = findViewById<TextView>(R.id.txtEmailEmployeeDetails)
        val txtJobTitle = findViewById<TextView>(R.id.txtJobTitleEmployeeDetails)
        val txtPhone = findViewById<TextView>(R.id.txtPhoneEmployeeDetails)
        val imgAvatar = findViewById<ImageView>(R.id.imgPictureEmployeeDetails)
        val txtCode = findViewById<TextView>(R.id.txtCodeEmployeeDetails)

        val btnEdit = findViewById<ImageButton>(R.id.imgButtonEditEmployeeDetails)
        val btnDelete = findViewById<ImageButton>(R.id.imgButtonDeleteEmployeeDetails)
        val btnReturn = findViewById<ImageButton>(R.id.imgButtonReturnEmployeeDetails)


        val employee = intent.getSerializableExtra("employee") as Employee

        txtId.text = "Id:  ${employee.id.toString()}"
        txtName.text = "Name: ${employee.name} "
        txtEmail.text = "E-mail: ${employee.email}"
        txtJobTitle.text = "Job Title: ${employee.jobTitle}"
        txtPhone.text = "Phone: ${employee.phone}"
        txtCode.text = "Code: ${employee.employeeCode}"

        Picasso.get().load(employee.imageUrl).fetch();
        Picasso.get().load(employee.imageUrl).into(imgAvatar)

        btnEdit.setOnClickListener {
            Toast.makeText(this, "Edit", Toast.LENGTH_SHORT).show()
        }

        btnDelete.setOnClickListener {
            Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show()
            showAlertDialog("Confirm Employee Exclusion?",null,View.OnClickListener {
                val gson = GsonBuilder().create()
                val retrofit = Retrofit.Builder().baseUrl(com.sampson.terceirokotlin.ui.home.BASE_URL).addConverterFactory(
                    GsonConverterFactory.create(gson)
                ).build()
                val apiService = retrofit.create(ApiService::class.java)
                apiService.removeEmployee(employee.id).enqueue(object : Callback<Void>{
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        Log.d("EmployeeDetailsActivity",response.code().toString())
                        Toast.makeText(applicationContext, "Employee deleted!",Toast.LENGTH_SHORT).show()
                        finish()
                    }

                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        Log.d("FAILURE",call.toString())
                    }
                })
            })

        }

        btnReturn.setOnClickListener {
            onBackPressed()
        }

    }

    private fun showAlertDialog(
        title : String,
        view : View?,
        positiveClickListener : View.OnClickListener
    ){
        AlertDialog.Builder(this)
            .setTitle(title)
            .setView(view)
            .setNegativeButton("Cancel",null)
            .setPositiveButton("OK") { _,_ ->
                positiveClickListener.onClick(null)
            }.show()
    }
}