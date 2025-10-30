package com.example.notesmanager.data

import android.icu.text.StringSearch
import androidx.core.app.Person
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.notesmanager.model.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNote(note: Note): Long

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("DELETE FROM note")
    suspend fun deleteAllNotes()

    @Query("SELECT * FROM note ORDER BY id ASC")
    fun readAllData(): LiveData<List<Note>>

    @Query("SELECT * FROM note WHERE title LIKE :searchQuery OR description LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<Note>>

}