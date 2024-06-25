package com.example.developershortcut.data

import android.content.Context
import androidx.room.Room
import com.example.developershortcut.data.database.NoteDatabase

object DataBaseProvider {

    private var database: NoteDatabase? = null

    fun getDatabase(context: Context): NoteDatabase {
        if (database == null) {
            database = Room.databaseBuilder(
                context.applicationContext,
                NoteDatabase::class.java, "note_database"
            ).build()
        }
        return database!!
    }
}
