package com.sampson.terceirokotlin

import Model.Employee
import Model.Repository
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class EditFragmentListAdapter : RecyclerView.Adapter<ViewHolderEditFragment>() {
    private val repository = Repository()
    private val list: List<Employee>?
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderEditFragment {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_view_item, parent, false)
        return ViewHolderEditFragment(view)
    }

    override fun onBindViewHolder(holder: ViewHolderEditFragment, position: Int) {
        holder.txtNameEmployee.text = list?.get(position)?.name
        holder.txtEmailEmployee.text = list?.get(position)?.email
        holder.txtJobTitleEmployee.text = list?.get(position)?.jobTitle
    }

    override fun getItemCount(): Int {
        return list!!.size
    }

    init {
        list = repository.populateList()
    }
}