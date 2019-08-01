package com.maxot.contactbook.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.maxot.contactbook.data.db.dao.ContactDao
import com.maxot.contactbook.data.db.entity.Contact

@Database(entities = [Contact::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun contactDao(): ContactDao

    companion object {
        var INSTANCE: AppDatabase? = null

        fun getAppDataBase(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "myDB").build()
                }
            }
            return INSTANCE
        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }

}