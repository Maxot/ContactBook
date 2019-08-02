package com.maxot.contactbook.ui.window.edit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maxot.contactbook.constant.ContactType
import com.maxot.contactbook.databinding.ItemInputDataBinding

class PhoneEmailEditRecycleAdapter(var data: ArrayList<String>, var contactType: ContactType) : RecyclerView.Adapter<PhoneEmailEditRecycleAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemInputDataBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], contactType)
    }

    fun getSize(): Int{
        return data.size - 1
    }
    fun addItem(){
        data.add("")
        notifyItemChanged(data.size)
    }

    class ViewHolder(var binding: ItemInputDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: String, contactType: ContactType) {
            binding.data = data
            binding.contactType = contactType
            binding.editText.requestFocus()
        }
    }
}