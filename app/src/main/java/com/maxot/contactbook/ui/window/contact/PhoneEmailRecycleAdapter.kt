package com.maxot.contactbook.ui.window.contact

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maxot.contactbook.constant.ContactType
import com.maxot.contactbook.databinding.ItemOutputDataBinding

class PhoneEmailRecycleAdapter(var data: ArrayList<String>,
                               private val contactType: ContactType,
                               val interactionHandler: ContactFragment.InteractionHandler) : RecyclerView.Adapter<PhoneEmailRecycleAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemOutputDataBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], contactType, interactionHandler)
    }

    class ViewHolder(var binding: ItemOutputDataBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(data: String, contactType: ContactType, interactionHandler: ContactFragment.InteractionHandler) {
            binding.data = data
            binding.contactType = contactType
            binding.handler = interactionHandler
        }
    }
}