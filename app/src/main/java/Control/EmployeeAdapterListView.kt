package Control

import Model.Employee
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.sampson.terceirokotlin.EmployeeDetailsActivity
import com.sampson.terceirokotlin.R

private const val TAG = "EmployeeAdapterListView"

class EmployeeAdapterListView(
    private val context: Context

) : RecyclerView.Adapter<EmployeeAdapterListView.EmployeeListViewHolder>() {

    var employees: List<Employee> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmployeeListViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.card_list_view_item, parent, false)
        return EmployeeListViewHolder(view)
    }

    override fun onBindViewHolder(holder: EmployeeListViewHolder, position: Int) {
        val currentEmployee = employees[position]
        holder.name.text = currentEmployee.name
        holder.jobTitle.text = currentEmployee.jobTitle

        holder.btnDetails.setOnClickListener {
            val intent = Intent(context, EmployeeDetailsActivity::class.java)
            intent.putExtra("employee", employees[position])
            context.startActivity(intent)
        }
    }

    override fun getItemCount() = employees.size

    class EmployeeListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.txtNameListView)
        val jobTitle = itemView.findViewById<TextView>(R.id.txtJobTitleListView)
        val btnDetails = itemView.findViewById<Button>(R.id.btnDetailListView)
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
        if (this.employees.size > 0) {
            notifyDataSetChanged()
            Toast.makeText(context,"${this.employees.size} name(s) found",Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context,"Name not found",Toast.LENGTH_SHORT).show()
        }
    }


}