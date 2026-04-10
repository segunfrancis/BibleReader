package com.blazesoftstudio.biblereader.core.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BibleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertTranslation(entity: TranslationEntity)

    @Insert
    suspend fun insertBook(entity: BookEntity): Long

    @Insert
    suspend fun insertChapter(entity: ChapterEntity): Long

    @Insert
    suspend fun insertChapterAudioLinks(entity: ChapterAudioLinkEntity)

    @Insert
    suspend fun insertChapterContent(items: List<ChapterContentEntity>)

    @Query("DELETE FROM books WHERE translationId = :translationId")
    suspend fun deleteBooksByTranslationId(translationId: String)

    @Query("DELETE FROM translations WHERE id = :translationId")
    suspend fun deleteTranslationById(translationId: String)
}
