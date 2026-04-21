package com.example.notesjetpackcompapp.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.notesjetpackcompapp.R
import com.example.notesjetpackcompapp.room.Note
import com.example.notesjetpackcompapp.viewmodel.NoteViewModel

@Composable
fun NoteScreen(viewModel: NoteViewModel = hiltViewModel()){

    // when DB updates --> Flow emits --> State Updates
    val notes by viewModel.allNotes.collectAsState(initial = emptyList())

    var title by remember { mutableStateOf("") }
    var content by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.backgroundimg),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(modifier = Modifier.fillMaxSize().padding(16.dp).padding(top = 45.dp)) {
            TextField(value = title,
                onValueChange = { title = it },
                label = { Text("Title") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            TextField(value = content,
                onValueChange = { content = it },
                label = { Text("Content") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    viewModel.insert(
                        Note(
                            0, title, content, System.currentTimeMillis()
                        )
                    )
                    title = ""
                    content = ""
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Add Note")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Display All Notes in DB
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(notes){ note ->
                    NoteItem(note)
                }
            }
        }
    }
}
