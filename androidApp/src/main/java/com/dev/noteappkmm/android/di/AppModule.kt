package com.dev.noteappkmm.android.di

import android.app.Application
import com.dev.noteappkmm.data.dataSource.NoteDataSourceImpl
import com.dev.noteappkmm.data.local.DatabaseDriverFactory
import com.dev.noteappkmm.database.NoteDatabase
import com.dev.noteappkmm.domain.dataSource.NoteDataSource
import com.squareup.sqldelight.db.SqlDriver
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
    fun provideSqlDriver(
        app: Application
    ): SqlDriver {
        return DatabaseDriverFactory(app).createDriver()
    }

    @Provides
    @Singleton
    fun provideDatabase(
        driver: SqlDriver
    ): NoteDatabase {
        return NoteDatabase(driver)
    }

    @Provides
    @Singleton
    fun provideNoteDataSource(
        db: NoteDatabase
    ): NoteDataSource {
        return NoteDataSourceImpl(db)
    }
}