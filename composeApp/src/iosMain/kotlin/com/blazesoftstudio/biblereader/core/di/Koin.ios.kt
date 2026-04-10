package com.blazesoftstudio.biblereader.core.di

import androidx.room.Room
import androidx.room.RoomDatabase
import com.blazesoftstudio.biblereader.core.data.HttpClientEngineFactory
import com.blazesoftstudio.biblereader.core.data.OnboardingStateStore
import com.blazesoftstudio.biblereader.core.data.local.BibleReaderDatabase
import io.ktor.client.engine.darwin.Darwin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

private const val DATABASE_NAME = "bible_reader.db"

actual fun getPlatformDatabaseBuilder(): RoomDatabase.Builder<BibleReaderDatabase> {
    val documentsPath =
        NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )?.path.orEmpty()

    return Room.databaseBuilder<BibleReaderDatabase>(
        name = "$documentsPath/$DATABASE_NAME"
    )
}

actual val platformModule = module {
    single<OnboardingStateStore> { InMemoryOnboardingStateStore() }
    single<HttpClientEngineFactory> {
        object : HttpClientEngineFactory {
            override fun create() = Darwin.create()
        }
    }
}

private class InMemoryOnboardingStateStore : OnboardingStateStore {
    private val state = MutableStateFlow(false)

    override val onboardingComplete: Flow<Boolean> = state

    override suspend fun setOnboardingComplete(completed: Boolean) {
        state.value = completed
    }
}
