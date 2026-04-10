package com.blazesoftstudio.biblereader.core.data.local

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor

@Database(
    entities = [
        TranslationEntity::class,
        BookEntity::class,
        ChapterEntity::class,
        ChapterAudioLinkEntity::class,
        ChapterContentEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
@ConstructedBy(BibleReaderDatabaseConstructor::class)
abstract class BibleReaderDatabase : RoomDatabase() {
    abstract fun bibleDao(): BibleDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object BibleReaderDatabaseConstructor : RoomDatabaseConstructor<BibleReaderDatabase> {
    override fun initialize(): BibleReaderDatabase
}
