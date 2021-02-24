package com.sampson.terceirokotlin

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.content_main.*
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
        val btnSubmit = view.findViewById<Button>(R.id.btnSubmitCreate)

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
            if(txtName.length() != 0 && txtEmail.length() != 0 && txtJobTitle.length() != 0 && txtPhone.length() != 0){
                Toast.makeText(context,"Submitting",Toast.LENGTH_SHORT).show()
                val fragment = Fragment(R.layout.fragment_home)
                fragmentManager?.beginTransaction()?.replace(R.id.nav_host_fragment,fragment)?.commit()
            }
        }

        return view
    }
}


