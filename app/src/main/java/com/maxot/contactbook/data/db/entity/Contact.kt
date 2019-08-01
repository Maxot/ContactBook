package com.maxot.contactbook.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.maxot.contactbook.data.db.converter.ListConverter


@Entity
@TypeConverters(ListConverter::class)
data class Contact(
        @PrimaryKey(autoGenerate = true) val id: Long? = null,
        val firstName: String,
        val secondName: String,
        val owner: String,
        val emails: List<String>,
        val phones: List<String>
)