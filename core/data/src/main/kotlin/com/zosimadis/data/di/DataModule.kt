package com.zosimadis.data.di

import com.zosimadis.data.BooksRepository
import com.zosimadis.data.repository.OfflineBooksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    internal abstract fun bindsBooksRepository(booksRepository: OfflineBooksRepository): BooksRepository
}
