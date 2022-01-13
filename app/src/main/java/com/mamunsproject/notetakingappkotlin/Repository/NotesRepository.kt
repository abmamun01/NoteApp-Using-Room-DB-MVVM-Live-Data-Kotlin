package com.mamunsproject.notetakingappkotlin.Repository

import androidx.lifecycle.LiveData
import com.mamunsproject.notetakingappkotlin.Model.NotesDao
import com.mamunsproject.notetakingappkotlin.Model.Notes_Entity

//Akhane Dao niyechi karon ja banabo akhane sob Dao r sahajje
class NotesRepository(val dao: NotesDao) {

    fun getAllNotes(): LiveData<List<Notes_Entity>> {
        return dao.getNotes()
    }


    fun getLowNotes():LiveData<List<Notes_Entity>> = dao.getLowNotes()
    fun getMediumNotes():LiveData<List<Notes_Entity>> = dao.getMediumNotes()
    fun getHighNotes():LiveData<List<Notes_Entity>> = dao.getHighNotes()


    fun insertNotes(notes: Notes_Entity) {
        dao.insertNotes(notes)
    }


    fun deleteNotes(id: Int) {
        dao.deleteNotes(id)
    }


    fun updateNotes(note: Notes_Entity) {
        dao.updateNotes(note)
    }
}