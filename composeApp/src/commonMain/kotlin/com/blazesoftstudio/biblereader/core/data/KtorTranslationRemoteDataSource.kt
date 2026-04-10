package com.blazesoftstudio.biblereader.core.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.serialization.Serializable

class KtorTranslationRemoteDataSource(
    private val client: HttpClient,
    private val endpointUrl: String,
) : TranslationRemoteDataSource {
    override suspend fun getTranslations(): List<BibleTranslation> {
        return client.get(endpointUrl).body<TranslationBaseDto>().translations
            .filter { translationDto -> translationDto.language == "eng" }
            .map { dto ->
                BibleTranslation(
                    id = dto.id,
                    name = dto.name,
                    website = dto.website,
                    language = dto.language,
                    shortName = dto.shortName,
                    licenseUrl = dto.licenseUrl,
                    numberOfBooks = dto.numberOfBooks,
                    availableFormats = dto.availableFormats,
                    listOfBooksApiLink = dto.listOfBooksApiLink,
                    totalNumberOfChapters = dto.totalNumberOfChapters,
                    totalNumberOfVerses = dto.totalNumberOfVerses,
                )
            }
    }
}

@Serializable
private data class TranslationDto(
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

@Serializable
private data class TranslationBaseDto(val translations: List<TranslationDto>)
