package com.blazesoftstudio.biblereader.core.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.blazesoftstudio.biblereader.core.data.BibleContentRemoteDataSource
import com.blazesoftstudio.biblereader.core.data.HttpClientEngineFactory
import com.blazesoftstudio.biblereader.core.data.KtorBibleContentRemoteDataSource
import com.blazesoftstudio.biblereader.core.data.KtorTranslationRemoteDataSource
import com.blazesoftstudio.biblereader.core.data.TranslationRemoteDataSource
import com.blazesoftstudio.biblereader.core.data.TranslationRepository
import com.blazesoftstudio.biblereader.core.data.local.BibleReaderDatabase
import com.blazesoftstudio.biblereader.feature.onboarding.OnboardingViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

private const val BIBLE_BASE_URL = "https://bible.helloao.org/api"
private const val TRANSLATIONS_URL = "$BIBLE_BASE_URL/available_translations.json"

val commonModule = module {
    single {
        HttpClient(get<HttpClientEngineFactory>().create()) {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        explicitNulls = false
                    }
                )
            }
            install(Logging) {
                level = LogLevel.BODY
                logger = Logger.DEFAULT
            }
        }
    }
    single<TranslationRemoteDataSource> {
        KtorTranslationRemoteDataSource(
            client = get(),
            endpointUrl = TRANSLATIONS_URL,
        )
    }
    single<BibleContentRemoteDataSource> {
        KtorBibleContentRemoteDataSource(
            client = get(),
            baseUrl = BIBLE_BASE_URL,
        )
    }
    single<BibleReaderDatabase> {
        getPlatformDatabaseBuilder()
            .setDriver(BundledSQLiteDriver())
            .setQueryCoroutineContext(Dispatchers.IO)
            .build()
    }
    single { get<BibleReaderDatabase>().bibleDao() }
    singleOf(::TranslationRepository)
    factory { OnboardingViewModel(get(), get()) }
}

expect fun getPlatformDatabaseBuilder(): androidx.room.RoomDatabase.Builder<BibleReaderDatabase>

expect val platformModule: Module

private var koinApplication: KoinApplication? = null

fun initKoin(appDeclaration: KoinAppDeclaration = {}): KoinApplication {
    val existing = koinApplication
    if (existing != null) return existing

    return startKoin {
        appDeclaration()
        modules(commonModule, platformModule)
    }.also { started ->
        koinApplication = started
    }
}
