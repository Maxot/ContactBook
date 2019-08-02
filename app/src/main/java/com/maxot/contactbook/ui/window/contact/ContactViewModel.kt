package com.maxot.contactbook.ui.window.contact

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.maxot.contactbook.data.ContactRepository
import com.maxot.contactbook.data.db.entity.Contact
import com.maxot.contactbook.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class ContactViewModel(application: Application) : BaseViewModel(application){
    private var repository = ContactRepository(application)

    val contact = MutableLiveData<Contact>()

    fun getContact(ownerName: String, id: Long){
        scope.launch {
            contact.postValue(repository.getContact(ownerName, id))
        }
    }

    val deleteContact = MutableLiveData<String>()

    fun deleteContact(contact: Contact){
        scope.launch {
            deleteContact.postValue(repository.deleteContact(contact))
        }
    }
}