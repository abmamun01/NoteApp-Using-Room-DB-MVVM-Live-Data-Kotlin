package com.mamunsproject.notetakingappkotlin.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Notes_Table")
data class Notes_Entity(

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    val title: String,
    val subtitle: String,
    val notes: String,
    val date: String,
    val priority: String
)