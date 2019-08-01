package com.maxot.contactbook.ui.window.edit

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.maxot.contactbook.data.ContactRepository
import com.maxot.contactbook.data.db.AppDatabase
import com.maxot.contactbook.data.db.entity.Contact
import com.maxot.contactbook.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class EditContactViewModel(application: Application) : BaseViewModel(application) {
    private var repository = ContactRepository(application)

    val insertStatus = MutableLiveData<String>()

    fun insertContact(contact: Contact){
        scope.launch {
            insertStatus.postValue(repository.inteserContact(contact))
        }
    }

    val contact = MutableLiveData<Contact>()

    fun getContact(ownerName: String, id: Long){
        scope.launch {
            contact.postValue(repository.getContact(ownerName, id))
        }
    }

}
