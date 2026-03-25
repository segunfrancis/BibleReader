package com.blazesoftstudio.biblereader

import androidx.compose.ui.window.ComposeUIViewController
import com.blazesoftstudio.biblereader.core.di.initKoin

fun MainViewController() = ComposeUIViewController {
    initKoin()
    App()
}
