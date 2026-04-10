package com.blazesoftstudio.biblereader.core.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class KtorBibleContentRemoteDataSource(
    private val client: HttpClient,
    private val baseUrl: String,
) : BibleContentRemoteDataSource {

    override suspend fun getCompleteTranslation(translationId: String): CompleteTranslationResponseDto {
        return client.get("$baseUrl/$translationId/complete.json").body()
    }
}
