package com.sampson.terceirokotlin

import android.provider.ContactsContract
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.card_view_item.view.*

class ViewHolderEditFragment(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val imgEmployee = itemView.imgPictureCardView
    val txtNameEmployee = itemView.txtNameCardView
    val txtEmailEmployee = itemView.txtEmailCardView
    val txtJobTitleEmployee = itemView.txtJobTitleCardView
    val btnDetalis = itemView.btnDetailCardView
}
