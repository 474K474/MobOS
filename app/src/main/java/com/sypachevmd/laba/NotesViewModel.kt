package com.sypachevmd.laba

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class NotesViewModel : ViewModel() {
    var notes = mutableStateOf(
        listOf(
            Note("Note 1", "This is the first note"),
            Note("Note 2", "This is the second note"),
            Note("Note 3", "Another note"),
            Note("Note 4", "Yet another note")
        )
    )
        private set

    var currentNote by mutableStateOf<Note?>(null)
        private set

    fun selectNote(note: Note) {
        currentNote = note
    }

    fun addNewNote() {
        currentNote = Note("", "")
    }

    fun saveNote() {
        currentNote?.let { note ->
            val updatedList = if (notes.value.contains(note)) {
                notes.value.map { if (it == note) note else it }
            } else {
                notes.value + note
            }
            notes.value = updatedList
            currentNote = null
        }
    }

    fun discardChanges() {
        currentNote = null
    }
}
