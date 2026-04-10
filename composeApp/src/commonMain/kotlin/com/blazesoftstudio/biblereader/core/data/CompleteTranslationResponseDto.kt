package com.blazesoftstudio.biblereader.core.data

import kotlinx.serialization.Serializable

@Serializable
data class CompleteTranslationResponseDto(
    val translation: CompleteTranslationDto,
    val books: List<CompleteBookDto>
)

@Serializable
data class CompleteTranslationDto(
    val id: String,
    val name: String,
    val website: String,
    val licenseUrl: String,
    val licenseNotes: String? = null,
    val shortName: String,
    val englishName: String,
    val language: String,
    val textDirection: String,
    val sha256: String,
    val availableFormats: List<String>,
    val listOfBooksApiLink: String,
    val completeTranslationApiLink: String,
    val numberOfBooks: Int,
    val totalNumberOfChapters: Int,
    val totalNumberOfVerses: Long,
    val languageName: String,
    val languageEnglishName: String,
)

@Serializable
data class CompleteBookDto(
    val id: String,
    val name: String,
    val commonName: String,
    val title: String,
    val order: Int,
    val numberOfChapters: Int,
    val totalNumberOfVerses: Int,
    val chapters: List<CompleteBookChapterWrapperDto>,
)

@Serializable
data class CompleteBookChapterWrapperDto(
    val numberOfVerses: Int,
    val thisChapterAudioLinks: ChapterAudioLinksDto? = null,
    val chapter: CompleteChapterDto,
)

@Serializable
data class ChapterAudioLinksDto(
    val gilbert: String? = null,
    val hays: String? = null,
    val souer: String? = null,
)

@Serializable
data class CompleteChapterDto(
    val number: Int,
    val content: List<ChapterContentDto>,
)

@Serializable
data class ChapterContentDto(
    val type: String,
    val number: Int? = null,
    val content: List<String>? = null,
)
