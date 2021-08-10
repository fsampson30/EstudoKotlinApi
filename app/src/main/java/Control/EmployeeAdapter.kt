package Control

import Model.Employee
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sampson.terceirokotlin.R
import com.squareup.picasso.Picasso

class EmployeeAdapter(
    private val context: Context

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
    }

    override fun getItemCount() = employees.size

    class EmployeeListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.txtNameCardView)
        val email = itemView.findViewById<TextView>(R.id.txtEmailCardView)
        val jobTitle = itemView.findViewById<TextView>(R.id.txtJobTitleCardView)
        val imageUrl = itemView.findViewById<ImageView>(R.id.imgPictureCardView)
    }

    fun setEmployeeList(employeeList: List<Employee>) {
        this.employees = employeeList
        notifyDataSetChanged()
    }


}