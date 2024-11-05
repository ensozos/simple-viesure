package com.zosimadis.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.zosimadis.database.dao.BookDao
import com.zosimadis.database.model.BookEntity

@Database(
    entities = [BookEntity::class],
    version = 1,
    exportSchema = true,
)
internal abstract class ViesureDatabase : RoomDatabase() {
    abstract fun bookDao(): BookDao
}
