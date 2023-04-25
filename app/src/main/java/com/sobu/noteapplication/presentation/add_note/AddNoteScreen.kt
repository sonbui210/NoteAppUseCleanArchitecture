package com.sobu.noteapplication.presentation.add_note

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.sobu.noteapplication.presentation.util.AddEditNoteEvent


@Composable
fun AddNoteScreen(
    navController: NavController,
    viewNodel: AddNoteViewNodel = hiltViewModel()
) {
    val tilteState = viewNodel.noteTitle.value
    val contentState = viewNodel.noteContent.value
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (tilteState.isBlank()) {
                        Toast.makeText(context,"Title ko trong", Toast.LENGTH_LONG).show()
                        return@FloatingActionButton
                    }
                    if (contentState.isBlank()) {
                        Toast.makeText(context,"Content ko trong", Toast.LENGTH_LONG).show()
                        return@FloatingActionButton
                    }
                    Log.d("sonbui","tilteState: $tilteState, contentState: $contentState ")
                    viewNodel.onEvent(AddEditNoteEvent.SaveNote)
                    navController.navigateUp()
                },
                backgroundColor = MaterialTheme.colors.primary,
                modifier = Modifier
            ) {
                Icon(imageVector = Icons.Default.Save, contentDescription = "Save note")
            }
        },
        scaffoldState = scaffoldState
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)) {
            OutlinedTextField(value = tilteState, onValueChange = {
                viewNodel.onEvent(AddEditNoteEvent.EnteredTitle(it))
            },
                singleLine = true,
                textStyle = MaterialTheme.typography.h6,
                modifier = Modifier.fillMaxWidth(),
                label = { Text(text = "Title")}
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(value = contentState, onValueChange = {
                viewNodel.onEvent(AddEditNoteEvent.EnteredContent(it))
            },
                singleLine = false,
                textStyle = MaterialTheme.typography.body1,
                modifier = Modifier.fillMaxSize().padding(bottom = 60.dp),
                label = { Text(text = "Content")}
            )
        }




    }


}