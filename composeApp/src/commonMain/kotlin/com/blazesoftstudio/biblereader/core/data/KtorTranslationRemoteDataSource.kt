package com.blazesoftstudio.biblereader.core.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

class KtorTranslationRemoteDataSource(
    private val client: HttpClient,
    private val endpointUrl: String,
) : TranslationRemoteDataSource {
    override suspend fun getTranslations(): List<BibleTranslation> {
        return client.get(endpointUrl).body<List<TranslationDto>>().map { dto ->
            BibleTranslation(
                id = dto.id,
                language = dto.language,
                abbreviation = dto.abbreviation,
                name = dto.name,
                sizeInMb = dto.sizeInMb,
            )
        }
    }
}

@Serializable
private data class TranslationDto(
    val id: String,
    val language: String,
    val abbreviation: String,
    val name: String,
    @SerialName("size_mb")
    val sizeInMb: Double,
)
