package com.example.developershortcut.screen.threescreen

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.developershortcut.data.database.NoteDatabase
import com.example.developershortcut.data.database.repository.NoteRepository
import com.example.developershortcut.data.database.entities.NoteEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: NoteRepository

    val allNoteEntity: Flow<List<NoteEntity>>

    init {
        val noteEntityDao = NoteDatabase.getDatabase(application).getNoteDao()
        repository = NoteRepository(noteEntityDao)
        allNoteEntity = repository.allNoteEntity
    }


    fun addNote(noteEntity: NoteEntity) {
        viewModelScope.launch {
            repository.insert(noteEntity)
        }
    }

    fun deleteNote(note: NoteEntity) {
        viewModelScope.launch {
            repository.deleteNote(note)
            // loadNotes()
        }
    }

    /*    private fun loadNotes() {
            viewModelScope.launch {
                _tasks.value = noteDao.getAllNotes()
            }
        }


        fun updateNote(note: NoteEntity) {
            viewModelScope.launch {
                noteDao.updateNote(note)
                loadNotes()
            }
        }


        }*/
}