package com.zosimadis.database.di

import android.content.Context
import androidx.room.Room
import com.zosimadis.database.ViesureDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
    ): ViesureDatabase = Room.databaseBuilder(
        context,
        ViesureDatabase::class.java,
        "viesure_database",
    ).build()
}
