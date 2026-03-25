package com.blazesoftstudio.biblereader.feature.onboarding.model

import com.blazesoftstudio.biblereader.core.data.BibleTranslation

data class OnboardingUiState(
    val loadingTranslations: Boolean = false,
    val downloadingTranslations: Boolean = false,
    val translations: List<BibleTranslation> = emptyList(),
    val selectedIds: Set<String> = emptySet(),
)
