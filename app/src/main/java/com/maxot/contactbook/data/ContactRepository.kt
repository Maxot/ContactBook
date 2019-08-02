package com.maxot.contactbook.data

import android.content.Context
import com.maxot.contactbook.data.db.AppDatabase
import com.maxot.contactbook.data.db.entity.Contact

class ContactRepository(val context: Context) {

    val db = AppDatabase.getAppDataBase(context)
    var contactDao = db?.contactDao()

    suspend fun insertContact(contact: Contact): String {
        var res : Long? = null
        res = contactDao?.insertContact(contact)
        return if (res == null)
            "Error of inserting" else
            "Inserting is succeed"
    }

    suspend fun getAllContact(ownerName: String): List<Contact>? {
        return contactDao?.getContactsByOwner(ownerName)
    }

    suspend fun getContact(ownerName: String, id: Long): Contact? {
        return contactDao?.getContact(ownerName, id)
    }

    suspend fun deleteContact(contact: Contact): String {
        contactDao?.deleteContact(contact)
        return "Contact is deleted"
    }
}