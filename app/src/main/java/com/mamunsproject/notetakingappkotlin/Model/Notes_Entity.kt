package com.mamunsproject.notetakingappkotlin.Model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "Notes_Table")
@Parcelize
data class Notes_Entity(

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    val title: String,
    val subtitle: String,
    val notes: String,
    val date: String,
    val priority: String
) : Parcelable