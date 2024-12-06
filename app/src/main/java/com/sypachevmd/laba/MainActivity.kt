package com.sypachevmd.laba

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.sypachevmd.laba.ui.NotesScreen
import com.sypachevmd.laba.ui.theme.LabaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Создание ViewModel через ViewModelProvider
        val notesViewModel = ViewModelProvider(this).get(NotesViewModel::class.java)

        setContent {
            LabaTheme {
                NotesScreen(viewModel = notesViewModel)
            }
        }
    }
}
