package com.blazesoftstudio.biblereader.core.di

import com.blazesoftstudio.biblereader.core.data.HttpClientEngineFactory
import com.blazesoftstudio.biblereader.core.data.OnboardingStateStore
import io.ktor.client.engine.darwin.Darwin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import org.koin.dsl.module

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
