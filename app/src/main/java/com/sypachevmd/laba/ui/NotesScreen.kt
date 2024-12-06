package com.sypachevmd.laba.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.sypachevmd.laba.Note
import com.sypachevmd.laba.NotesViewModel
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add


@Composable
fun NotesScreen(viewModel: NotesViewModel) {
    val currentNote = viewModel.currentNote
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                if (currentNote == null) viewModel.addNewNote()
                else viewModel.discardChanges()
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add"
                )
            }
        }
    ) { padding ->
        if (currentNote == null) {
            NotesList(
                notes = viewModel.notes.value,
                onNoteClick = viewModel::selectNote,
                modifier = Modifier.padding(padding)
            )
        } else {
            NoteEditor(
                note = currentNote,
                onTitleChange = { currentNote.title = it },
                onTextChange = { currentNote.text = it },
                onSave = viewModel::saveNote,
                modifier = Modifier.padding(padding)
            )
        }
    }
}

@Composable
fun NotesList(notes: List<Note>, onNoteClick: (Note) -> Unit, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
    ) {
        items(notes.size) { index ->
            val note = notes[index]
            Card(
                onClick = { onNoteClick(note) },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(note.title, modifier = Modifier.padding(16.dp))
            }
        }
    }
}

@Composable
fun NoteEditor(
    note: Note,
    onTitleChange: (String) -> Unit,
    onTextChange: (String) -> Unit,
    onSave: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        BasicTextField(
            value = remember { TextFieldValue(note.title) },
            onValueChange = { onTitleChange(it.text) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        BasicTextField(
            value = remember { TextFieldValue(note.text) },
            onValueChange = { onTextChange(it.text) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        )
        Button(
            onClick = onSave,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save")
        }
    }
}
