package com.example.notesmanager.repository

import androidx.lifecycle.LiveData
import androidx.room.Query
import com.example.notesmanager.data.NoteDao
import com.example.notesmanager.model.Note
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NoteDao) {

    val readAllData: LiveData<List<Note>> = noteDao.readAllData()

    suspend fun addNote(note: Note){
        noteDao.addNote(note)
    }

    suspend fun updateNote(note: Note){
        noteDao.updateNote(note)
    }

    suspend fun deleteNote(note: Note){
        noteDao.deleteNote(note)
    }

    suspend fun deleteAllNotes(){
        noteDao.deleteAllNotes()
    }

    fun searchDatabase(searchQuery: String): Flow<List<Note>>{
        return noteDao.searchDatabase(searchQuery)
    }
}