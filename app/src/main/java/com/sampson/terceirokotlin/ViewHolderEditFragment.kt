package com.sampson.terceirokotlin

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_list_view_item.view.*
import kotlinx.android.synthetic.main.card_view_item.view.*
import kotlinx.android.synthetic.main.card_view_item.view.btnDetailCardView

class ViewHolderEditFragment(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val imgEmployee = itemView.imgPictureCardView
    val txtNameEmployee = itemView.txtNameListView
    val txtEmailEmployee = itemView.txtEmailCardView
    val txtJobTitleEmployee = itemView.txtJobTitleListView
    val btnDetalis = itemView.btnDetailCardView
}
