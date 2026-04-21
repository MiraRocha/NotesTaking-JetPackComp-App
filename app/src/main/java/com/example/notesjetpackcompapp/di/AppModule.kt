package com.example.notesjetpackcompapp.di

import android.content.Context
import com.example.notesjetpackcompapp.room.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// a hilt module is a class where you define how to
// build and provide dependencies(objects like Room,
// Dao, repositories, retrofit, etc...)
// @Module: to register it as a Hilt component provide
// functions inside are annotated with @Provides to
// tell Hilt how to create instances
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(@ApplicationContext context: Context): NoteDatabase {
        return NoteDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideNoteDao(noteDatabase: NoteDatabase) = noteDatabase.noteDao()

}

