package com.zosimadis.database.di

import com.zosimadis.database.ViesureDatabase
import com.zosimadis.database.dao.BookDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaoModule {

    @Provides
    fun provideBookDao(
        db: ViesureDatabase,
    ): BookDao = db.bookDao()
}
