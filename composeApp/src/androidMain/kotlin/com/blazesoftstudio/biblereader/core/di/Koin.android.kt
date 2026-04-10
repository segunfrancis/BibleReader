package com.blazesoftstudio.biblereader.core.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.blazesoftstudio.biblereader.core.data.AndroidOnboardingStateStore
import com.blazesoftstudio.biblereader.core.data.HttpClientEngineFactory
import com.blazesoftstudio.biblereader.core.data.OnboardingStateStore
import com.blazesoftstudio.biblereader.core.data.local.BibleReaderDatabase
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val DATABASE_NAME = "bible_reader.db"

actual fun getPlatformDatabaseBuilder(): RoomDatabase.Builder<BibleReaderDatabase> {
    return Room.databaseBuilder<BibleReaderDatabase>(
        context = org.koin.core.context.GlobalContext.get().get<Context>(),
        name = DATABASE_NAME,
    )
}

actual val platformModule = module {
    single<OnboardingStateStore> { AndroidOnboardingStateStore(androidContext()) }
    single<HttpClientEngineFactory> {
        object : HttpClientEngineFactory {
            override fun create() = OkHttp.create()
        }
    }
}
