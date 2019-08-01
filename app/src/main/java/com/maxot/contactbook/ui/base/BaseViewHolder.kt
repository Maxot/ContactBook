package com.maxot.contactbook.ui.base


import androidx.recyclerview.widget.RecyclerView
import android.view.View

abstract class BaseViewHolder(view: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(view){

    open fun bind(string: String){

    }
}