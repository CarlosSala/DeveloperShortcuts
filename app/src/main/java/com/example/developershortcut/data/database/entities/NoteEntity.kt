package com.example.developershortcut.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class NoteEntity(
    var title: String,
    var description: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    // val isCompleted: Boolean = false
)

