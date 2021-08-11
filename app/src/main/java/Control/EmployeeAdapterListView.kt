package Control

import Model.Employee
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
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
    }

    override fun getItemCount() = employees.size

    class EmployeeListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.txtNameListView)
        val jobTitle = itemView.findViewById<TextView>(R.id.txtJobTitleListView)
    }

    fun setEmployeeList(employeeList: List<Employee>) {
        this.employees = employeeList
        notifyDataSetChanged()
    }

    fun sortByName(){
        Log.d(TAG, "Names: ${employees[0].name} , ${employees[1].name}")
        this.employees = this.employees.sortedBy { it.name }
        Log.d(TAG, "Names: ${employees[0].name} , ${employees[1].name}")
        notifyDataSetChanged()
    }


}