package com.blazesoftstudio.biblereader.core.navigation

import kotlinx.serialization.Serializable

sealed class HydroHeroNavigation {
    @Serializable
    data object Onboarding : HydroHeroNavigation()

    @Serializable
    data object TranslationSelection : HydroHeroNavigation()

    @Serializable
    data object Home : HydroHeroNavigation()
}
