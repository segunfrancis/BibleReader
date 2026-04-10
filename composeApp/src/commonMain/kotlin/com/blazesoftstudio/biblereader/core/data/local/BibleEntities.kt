package com.blazesoftstudio.biblereader.core.data.local

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(tableName = "translations")
data class TranslationEntity(
    @PrimaryKey val id: String,
    val name: String,
    val website: String,
    val licenseUrl: String,
    val licenseNotes: String?,
    val shortName: String,
    val englishName: String,
    val language: String,
    val textDirection: String,
    val sha256: String,
    val availableFormats: String,
    val listOfBooksApiLink: String,
    val completeTranslationApiLink: String,
    val numberOfBooks: Int,
    val totalNumberOfChapters: Int,
    val totalNumberOfVerses: Long,
    val languageName: String,
    val languageEnglishName: String,
)

@Entity(
    tableName = "books",
    foreignKeys = [
        ForeignKey(
            entity = TranslationEntity::class,
            parentColumns = ["id"],
            childColumns = ["translationId"],
            onDelete = ForeignKey.CASCADE,
        )
    ],
    indices = [Index("translationId"), Index(value = ["translationId", "bookId"], unique = true)],
)
data class BookEntity(
    @PrimaryKey(autoGenerate = true) val localId: Long = 0,
    val translationId: String,
    val bookId: String,
    val name: String,
    val commonName: String,
    val title: String,
    val bookOrder: Int,
    val numberOfChapters: Int,
    val totalNumberOfVerses: Int,
)

@Entity(
    tableName = "chapters",
    foreignKeys = [
        ForeignKey(
            entity = BookEntity::class,
            parentColumns = ["localId"],
            childColumns = ["bookLocalId"],
            onDelete = ForeignKey.CASCADE,
        )
    ],
    indices = [Index("bookLocalId"), Index(value = ["bookLocalId", "chapterNumber"], unique = true)],
)
data class ChapterEntity(
    @PrimaryKey(autoGenerate = true) val localId: Long = 0,
    val bookLocalId: Long,
    val chapterNumber: Int,
    val numberOfVerses: Int,
)

@Entity(
    tableName = "chapter_audio_links",
    foreignKeys = [
        ForeignKey(
            entity = ChapterEntity::class,
            parentColumns = ["localId"],
            childColumns = ["chapterLocalId"],
            onDelete = ForeignKey.CASCADE,
        )
    ],
    indices = [Index("chapterLocalId")],
)
data class ChapterAudioLinkEntity(
    @PrimaryKey(autoGenerate = true) val localId: Long = 0,
    val chapterLocalId: Long,
    val gilbert: String?,
    val hays: String?,
    val souer: String?,
)

@Entity(
    tableName = "chapter_content",
    foreignKeys = [
        ForeignKey(
            entity = ChapterEntity::class,
            parentColumns = ["localId"],
            childColumns = ["chapterLocalId"],
            onDelete = ForeignKey.CASCADE,
        )
    ],
    indices = [Index("chapterLocalId"), Index(value = ["chapterLocalId", "itemOrder"], unique = true)],
)
data class ChapterContentEntity(
    @PrimaryKey(autoGenerate = true) val localId: Long = 0,
    val chapterLocalId: Long,
    val itemOrder: Int,
    val type: String,
    val verseNumber: Int?,
    val textContent: String?,
)
