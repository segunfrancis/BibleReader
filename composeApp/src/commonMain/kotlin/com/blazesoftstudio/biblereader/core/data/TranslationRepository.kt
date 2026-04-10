package com.blazesoftstudio.biblereader.core.data

import androidx.room.withTransaction
import com.blazesoftstudio.biblereader.core.data.local.BibleDao
import com.blazesoftstudio.biblereader.core.data.local.BibleReaderDatabase
import com.blazesoftstudio.biblereader.core.data.local.BookEntity
import com.blazesoftstudio.biblereader.core.data.local.ChapterAudioLinkEntity
import com.blazesoftstudio.biblereader.core.data.local.ChapterContentEntity
import com.blazesoftstudio.biblereader.core.data.local.ChapterEntity
import com.blazesoftstudio.biblereader.core.data.local.TranslationEntity

class TranslationRepository(
    private val remoteDataSource: TranslationRemoteDataSource,
    private val bibleContentRemoteDataSource: BibleContentRemoteDataSource,
    private val bibleReaderDatabase: BibleReaderDatabase,
    private val bibleDao: BibleDao,
) {
    suspend fun fetchTranslations(): List<BibleTranslation> {
        return remoteDataSource.getTranslations()
    }

    suspend fun downloadTranslations(selectedIds: Set<String>) {
        if (selectedIds.isEmpty()) return

        selectedIds.forEach { translationId ->
            val response = bibleContentRemoteDataSource.getCompleteTranslation(translationId)
            persistTranslation(response)
        }
    }

    private suspend fun persistTranslation(response: CompleteTranslationResponseDto) {
        bibleReaderDatabase.withTransaction {
                bibleDao.deleteBooksByTranslationId(response.translation.id)
                bibleDao.deleteTranslationById(response.translation.id)

                val translation = response.translation
                bibleDao.upsertTranslation(
                    TranslationEntity(
                        id = translation.id,
                        name = translation.name,
                        website = translation.website,
                        licenseUrl = translation.licenseUrl,
                        licenseNotes = translation.licenseNotes,
                        shortName = translation.shortName,
                        englishName = translation.englishName,
                        language = translation.language,
                        textDirection = translation.textDirection,
                        sha256 = translation.sha256,
                        availableFormats = translation.availableFormats.joinToString(","),
                        listOfBooksApiLink = translation.listOfBooksApiLink,
                        completeTranslationApiLink = translation.completeTranslationApiLink,
                        numberOfBooks = translation.numberOfBooks,
                        totalNumberOfChapters = translation.totalNumberOfChapters,
                        totalNumberOfVerses = translation.totalNumberOfVerses,
                        languageName = translation.languageName,
                        languageEnglishName = translation.languageEnglishName,
                    )
                )

                response.books.forEach { book ->
                    val bookLocalId = bibleDao.insertBook(
                        BookEntity(
                            translationId = translation.id,
                            bookId = book.id,
                            name = book.name,
                            commonName = book.commonName,
                            title = book.title,
                            bookOrder = book.order,
                            numberOfChapters = book.numberOfChapters,
                            totalNumberOfVerses = book.totalNumberOfVerses,
                        )
                    )

                    book.chapters.forEach { chapterWrapper ->
                        val chapterLocalId = bibleDao.insertChapter(
                            ChapterEntity(
                                bookLocalId = bookLocalId,
                                chapterNumber = chapterWrapper.chapter.number,
                                numberOfVerses = chapterWrapper.numberOfVerses,
                            )
                        )

                        chapterWrapper.thisChapterAudioLinks?.let { links ->
                            bibleDao.insertChapterAudioLinks(
                                ChapterAudioLinkEntity(
                                    chapterLocalId = chapterLocalId,
                                    gilbert = links.gilbert,
                                    hays = links.hays,
                                    souer = links.souer,
                                )
                            )
                        }

                        val contentEntities = chapterWrapper.chapter.content.mapIndexed { index, content ->
                            ChapterContentEntity(
                                chapterLocalId = chapterLocalId,
                                itemOrder = index,
                                type = content.type,
                                verseNumber = content.number,
                                textContent = content.content?.joinToString("\n"),
                            )
                        }

                        if (contentEntities.isNotEmpty()) {
                            bibleDao.insertChapterContent(contentEntities)
                        }
                    }
                }
        }
    }
}
