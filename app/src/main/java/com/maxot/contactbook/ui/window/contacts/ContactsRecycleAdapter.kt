package com.maxot.contactbook.ui.window.contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.maxot.contactbook.data.db.entity.Contact
import com.maxot.contactbook.databinding.ItemContactBinding

class ContactsRecycleAdapter(var data: List<Contact>, var interactionHandler: ContactsFragment.InteractionHandler) : RecyclerView.Adapter<ContactsRecycleAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemContactBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position], interactionHandler)
    }


    class ViewHolder(var binding: ItemContactBinding): RecyclerView.ViewHolder(binding.root){

        fun  bind(data: Contact, interactionHandler: ContactsFragment.InteractionHandler){
            binding.data =  data
            binding.handler = interactionHandler
        }
    }
}