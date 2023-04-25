package com.sobu.noteapplication.di

import android.app.Application
import androidx.room.Room
import com.sobu.noteapplication.data.data_source.NoteDatabase
import com.sobu.noteapplication.data.repository.NoteRepositoryImpl
import com.sobu.noteapplication.domain.repository.NoteRepository
import com.sobu.noteapplication.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(application: Application) = Room.databaseBuilder(
        application,
        NoteDatabase::class.java,
        NoteDatabase.DB_NAME
    ).build()


    @Provides
    @Singleton
    fun provideNoteRepository(noteDatabase: NoteDatabase) : NoteRepository =
        NoteRepositoryImpl(noteDatabase.noteDao)


    @Provides
    @Singleton
    fun provideNoteUseCases(noteRepository: NoteRepository) : NoteUseCases =
        NoteUseCases(
            getNotes = GetNotes(noteRepository),
            deleteNote = DeleteNote(noteRepository),
            addNote = AddNote(noteRepository),
            updateNote = UpdateNote(noteRepository),
            getNote = GetNote(noteRepository)
        )


}