package com.blazesoftstudio.biblereader.core.data

data class BibleTranslation(
    val id: String,
    val name: String,
    val website: String,
    val language: String,
    val shortName: String,
    val licenseUrl: String,
    val numberOfBooks: Int,
    val totalNumberOfChapters: Int,
    val totalNumberOfVerses: Long,
    val listOfBooksApiLink: String,
    val availableFormats: List<String>
)
