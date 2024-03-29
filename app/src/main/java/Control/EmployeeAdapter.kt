package Control

import Model.Employee
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.sampson.terceirokotlin.EmployeeDetailsActivity
import com.sampson.terceirokotlin.R
import com.squareup.picasso.Picasso

class EmployeeAdapter(
    private val context: Context,

) : RecyclerView.Adapter<EmployeeAdapter.EmployeeListHolder>() {

    var employees: List<Employee> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeListHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_view_item, parent, false)
        return EmployeeListHolder(view)
    }

    override fun onBindViewHolder(holder: EmployeeListHolder, position: Int) {
        val currentEmployee = employees[position]
        holder.name.text = currentEmployee.name
        holder.email.text = currentEmployee.email
        holder.jobTitle.text = currentEmployee.jobTitle
        Picasso.get().load(currentEmployee.imageUrl).fetch();
        Picasso.get().load(currentEmployee.imageUrl).into(holder.imageUrl)

        holder.btnDetails.setOnClickListener {
            val intent = Intent(context, EmployeeDetailsActivity::class.java)
            intent.putExtra("employee", employees[position])
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = employees.size

    class EmployeeListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.txtNameCardView)
        val email = itemView.findViewById<TextView>(R.id.txtEmailCardView)
        val jobTitle = itemView.findViewById<TextView>(R.id.txtJobTitleCardView)
        val imageUrl = itemView.findViewById<ImageView>(R.id.imgPictureCardView)
        val btnDetails = itemView.findViewById<Button>(R.id.btnDetailCardView)
    }

    fun setEmployeeList(employeeList: List<Employee>) {
        this.employees = employeeList
        notifyDataSetChanged()
    }

    fun sortByName(){
        this.employees = this.employees.sortedBy { it.name }
        notifyDataSetChanged()
    }

    fun searchByName(name : String) {
        this.employees = this.employees.filter { it.name.toLowerCase().contains(name.toLowerCase()) }
        if (this.employees.isNotEmpty()) {
            notifyDataSetChanged()
            Toast.makeText(context,"${this.employees.size} name(s) found",Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context,"Name not found", Toast.LENGTH_SHORT).show()
        }
    }


}