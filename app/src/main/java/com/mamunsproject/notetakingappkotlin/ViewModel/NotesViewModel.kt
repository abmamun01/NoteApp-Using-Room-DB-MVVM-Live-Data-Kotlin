package com.mamunsproject.notetakingappkotlin.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.mamunsproject.notetakingappkotlin.Database.RoomDatabaseNote
import com.mamunsproject.notetakingappkotlin.Model.Notes_Entity
import com.mamunsproject.notetakingappkotlin.Repository.NotesRepository

//View model r modde repository r reference nite hoy
class NotesViewModel(application: Application) : AndroidViewModel(application) {

    val repository: NotesRepository


    //init sobar age run hobe  atar kaj atai
    init {

        val dao = RoomDatabaseNote.getDatabaseInstance(application).myNotesDao()
        repository = NotesRepository(dao)

    }

    fun addNotes(notes:Notes_Entity){

        repository.insertNotes(notes)
    }
}