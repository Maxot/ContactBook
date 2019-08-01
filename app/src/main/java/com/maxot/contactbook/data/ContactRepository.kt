package com.maxot.contactbook.data

import android.content.Context
import com.maxot.contactbook.data.db.AppDatabase
import com.maxot.contactbook.data.db.entity.Contact

class ContactRepository(val context: Context) {

    val db = AppDatabase.getAppDataBase(context)
    var contactDao = db?.contactDao()

    suspend fun inteserContact(contact: Contact): String {
        contactDao?.insertContact(contact)

        return "Success"
    }

    suspend fun getAllContact(ownerName: String): List<Contact>? {
        return contactDao?.getContactsByOwner(ownerName)

    }

    suspend fun getContact(ownerName: String, id: Long): Contact? {
        return contactDao?.getContact(ownerName, id)

    }
}