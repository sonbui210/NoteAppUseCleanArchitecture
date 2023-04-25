package com.sobu.noteapplication.presentation.add_note

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sobu.noteapplication.domain.model.Note
import com.sobu.noteapplication.domain.use_case.NoteUseCases
import com.sobu.noteapplication.presentation.util.AddEditNoteEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddNoteViewNodel @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModel() {


    private val _noteTitle = mutableStateOf("")
    val noteTitle: State<String> = _noteTitle

    private val _noteContent = mutableStateOf("")
    val noteContent : State<String> = _noteContent


    fun onEvent(event: AddEditNoteEvent) {
        when (event) {
            is AddEditNoteEvent.EnteredTitle -> {
                _noteTitle.value = event.value
            }
            is AddEditNoteEvent.EnteredContent -> {
                _noteContent.value = event.value
            }
            is AddEditNoteEvent.SaveNote -> {
                viewModelScope.launch {
                    noteUseCases.addNote(
                        Note(
                            noteTitle.value,
                            noteContent.value,
                            System.currentTimeMillis()
                        )
                    )
                }
            }
        }
    }

}