package com.blazesoftstudio.biblereader.core.data

interface TranslationRemoteDataSource {
    suspend fun getTranslations(): List<BibleTranslation>
}
