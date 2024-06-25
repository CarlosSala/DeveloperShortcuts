package com.example.developershortcut.data.database.repository

import com.example.developershortcut.data.database.dao.NoteDao
import com.example.developershortcut.data.database.entities.NoteEntity
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NoteDao) {

    val allNoteEntity: Flow<List<NoteEntity>> = noteDao.getAllNotes()

    suspend fun insert(text: String) {
        noteDao.insertNoteEntity(NoteEntity(text, "custom_body"))
    }
}
