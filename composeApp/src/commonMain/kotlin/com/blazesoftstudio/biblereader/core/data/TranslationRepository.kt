package com.blazesoftstudio.biblereader.core.data

import kotlinx.coroutines.delay

class TranslationRepository(
    private val remoteDataSource: TranslationRemoteDataSource,
) {
    suspend fun fetchTranslations(): List<BibleTranslation> {
        return remoteDataSource.getTranslations()
    }

    suspend fun downloadTranslations(selectedIds: Set<String>) {
        if (selectedIds.isEmpty()) return
        delay(1_000)
    }
}
