package com.maxot.contactbook.data.db.dao

import androidx.room.*
import com.maxot.contactbook.data.db.entity.Contact

@Dao
interface ContactDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContact(contact: Contact)

    @Update
    suspend fun updateContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("SELECT * FROM Contact WHERE owner == :owner")
    suspend fun getContactsByOwner(owner: String): List<Contact>

    @Query("SELECT * FROM Contact WHERE owner == :owner AND id == :id")
    suspend fun getContact(owner: String, id:Long): Contact

}