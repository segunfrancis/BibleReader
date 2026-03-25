package com.blazesoftstudio.biblereader.core.data

interface HttpClientEngineFactory {
    fun create(): io.ktor.client.engine.HttpClientEngine
}
