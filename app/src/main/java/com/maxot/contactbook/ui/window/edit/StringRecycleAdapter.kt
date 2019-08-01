package com.maxot.contactbook.ui.window.edit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maxot.contactbook.databinding.ItemInputDataBinding

class StringRecycleAdapter(var data: ArrayList<String>) : RecyclerView.Adapter<StringRecycleAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemInputDataBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun getSize(): Int{
        return data.size - 1
    }
    fun addItem(){
        data.add("")
        notifyItemChanged(data.size)
    }

    class ViewHolder(var binding: ItemInputDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: String) {
            binding.data = data
            binding.editText.requestFocus()

        }
    }
}