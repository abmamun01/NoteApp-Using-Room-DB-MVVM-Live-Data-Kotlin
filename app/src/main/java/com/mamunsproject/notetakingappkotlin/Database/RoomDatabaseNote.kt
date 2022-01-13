package com.mamunsproject.notetakingappkotlin.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mamunsproject.notetakingappkotlin.Model.NotesDao
import com.mamunsproject.notetakingappkotlin.Model.Notes_Entity

//abstrack jei function hobe setar body thakena
@Database(entities = [Notes_Entity::class], version = 1, exportSchema = false)
abstract class RoomDatabaseNote : RoomDatabase() {

    //Return korechi NotesDao r sathe
    abstract fun myNotesDao(): NotesDao

    //java te jemon static keyword kotlin companion object
    companion object {

        //Mane ata k easily access kora jabe
        @Volatile
        var INSTANCE: RoomDatabaseNote? = null

        // Return korbe RoomDatabaseNote
        fun getDatabaseInstance(context: Context): RoomDatabaseNote {

            val tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {

                val roomDatabaseInstance =
                    Room.databaseBuilder(context, RoomDatabaseNote::class.java, "Notes_Table")
                        .allowMainThreadQueries().build()

                INSTANCE = roomDatabaseInstance

                return roomDatabaseInstance

            }

        }
    }
}