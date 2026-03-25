package com.blazesoftstudio.biblereader.core.di

import com.blazesoftstudio.biblereader.core.data.AndroidOnboardingStateStore
import com.blazesoftstudio.biblereader.core.data.HttpClientEngineFactory
import com.blazesoftstudio.biblereader.core.data.OnboardingStateStore
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val platformModule = module {
    single<OnboardingStateStore> { AndroidOnboardingStateStore(androidContext()) }
    single<HttpClientEngineFactory> {
        object : HttpClientEngineFactory {
            override fun create() = OkHttp.create()
        }
    }
}
