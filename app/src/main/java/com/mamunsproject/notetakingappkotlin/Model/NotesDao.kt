package com.mamunsproject.notetakingappkotlin.Model

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface NotesDao {

    //Need query for read data
    @Query("SELECT * FROM NOTES_TABLE")
    fun getNotes(): LiveData<List<Notes_Entity>>

    // onConflict mane jodi same query pai tahole ata replace kore dibo
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNotes(notes: Notes_Entity)


    @Query("DELETE FROM Notes_Table WHERE id=:id")
    fun deleteNotes(id: Int)

    @Update
    fun updateNotes(notes: Notes_Entity)


}