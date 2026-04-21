package com.example.notesjetpackcompapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesjetpackcompapp.repository.NoteRepository
import com.example.notesjetpackcompapp.room.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

// ViewModel: Holds App/Ui Logic and Data
//            Fetches, hold & exposes data from repository
//            Works well with Jetpack Compose, Flow...
//            Manages coroutines using viewModelScope
@HiltViewModel
class NoteViewModel @Inject constructor(private val repository: NoteRepository) : ViewModel() {

    val allNotes: Flow<List<Note>> = repository.allNotesInDB

    fun insert(note: Note) = viewModelScope.launch {
        repository.insert(note)
    }
}