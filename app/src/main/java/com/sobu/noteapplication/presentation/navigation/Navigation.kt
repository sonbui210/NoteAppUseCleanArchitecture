package com.sobu.noteapplication.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.devcomentry.lib.composable
import com.sobu.noteapplication.presentation.add_note.AddNoteScreen
import com.sobu.noteapplication.presentation.notes.NotesScreen
import com.sobu.noteapplication.presentation.update_note.UpdateNoteScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.NotesScreen.route
    ) {

        composable(
            screen = Screen.NotesScreen
        ) {
            NotesScreen(navController = navController)
        }

        composable(
            screen = Screen.AddNote
        ) {
            AddNoteScreen(navController = navController)
        }

        // update screen with argument
        composable(
            screen = Screen.UpdateNote,
            arguments = UpdateScreenArgument()
        ) {
            UpdateNoteScreen(
                navController = navController
            )
        }
    }
}