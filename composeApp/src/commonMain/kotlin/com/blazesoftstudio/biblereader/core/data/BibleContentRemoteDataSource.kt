package com.blazesoftstudio.biblereader.core.data

interface BibleContentRemoteDataSource {
    suspend fun getCompleteTranslation(translationId: String): CompleteTranslationResponseDto
}
