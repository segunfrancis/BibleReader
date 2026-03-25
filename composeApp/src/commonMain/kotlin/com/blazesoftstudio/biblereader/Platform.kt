package com.blazesoftstudio.biblereader

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform