package com.mamunsproject.notetakingappkotlin.Database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mamunsproject.notetakingappkotlin.Model.NotesDao

//abstrack jei function hobe setar body thakena
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
                        .build()

                INSTANCE = roomDatabaseInstance

                return roomDatabaseInstance

            }

        }
    }
}