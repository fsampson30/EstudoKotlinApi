package com.sampson.terceirokotlin

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolderEditFragment(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var imgEmployee: ImageView = itemView.findViewById(R.id.imgPictureCardView)
    var txtNameEmployee: TextView = itemView.findViewById(R.id.txtNameCardView)
    var txtEmailEmployee: TextView = itemView.findViewById(R.id.txtEmailCardView)
    var txtJobTitleEmployee: TextView = itemView.findViewById(R.id.txtJobTitleCardView)
    var btnDetalis: Button = itemView.findViewById(R.id.btnDetailCardView)

}