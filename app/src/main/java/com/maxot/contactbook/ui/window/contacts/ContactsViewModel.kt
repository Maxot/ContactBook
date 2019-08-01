package com.maxot.contactbook.ui.window.contacts

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.maxot.contactbook.data.ContactRepository
import com.maxot.contactbook.data.db.entity.Contact
import com.maxot.contactbook.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class ContactsViewModel(application: Application) : BaseViewModel(application){
    private var repository = ContactRepository(application)

    val contacts = MutableLiveData<List<Contact>>()

    fun getContacts(ownerName: String){
        scope.launch {
            contacts.postValue(repository.getAllContact(ownerName))
        }
    }
}