package com.sampson.terceirokotlin

import Model.Employee
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.squareup.picasso.Picasso

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
        }

        btnReturn.setOnClickListener {
            onBackPressed()
        }

    }
}