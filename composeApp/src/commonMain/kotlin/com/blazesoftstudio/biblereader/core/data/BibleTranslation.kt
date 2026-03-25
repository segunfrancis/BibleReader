package com.blazesoftstudio.biblereader.core.data

data class BibleTranslation(
    val id: String,
    val language: String,
    val abbreviation: String,
    val name: String,
    val sizeInMb: Double,
)
