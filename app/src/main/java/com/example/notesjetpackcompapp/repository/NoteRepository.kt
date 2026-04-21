package com.example.notesjetpackcompapp.repository

import com.example.notesjetpackcompapp.room.Note
import com.example.notesjetpackcompapp.room.NoteDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// Abstracts the data source (Room, network,..)
// Exposes clean methods to the ViewModel/UI
// Makes it east to mock/fake data during testing
// Decouples your app layers
class NoteRepository @Inject constructor(private val noteDao: NoteDao) {

    suspend fun insert(note: Note) {
        noteDao.insert(note)
    }


    val allNotesInDB: Flow<List<Note>> = noteDao.getAllNotes()

}
