package com.blazesoftstudio.biblereader.feature.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.blazesoftstudio.biblereader.core.data.BibleTranslation
import com.blazesoftstudio.biblereader.core.data.OnboardingStateStore
import com.blazesoftstudio.biblereader.core.data.TranslationRepository
import com.blazesoftstudio.biblereader.feature.onboarding.model.OnboardingUiState
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class OnboardingViewModel(
    private val repository: TranslationRepository,
    private val onboardingStateStore: OnboardingStateStore,
) : ViewModel() {

    private val _uiState = MutableStateFlow(OnboardingUiState())
    val uiState: StateFlow<OnboardingUiState> = _uiState.asStateFlow()

    private val _effects = MutableSharedFlow<OnboardingEffect>()
    val effects: SharedFlow<OnboardingEffect> = _effects.asSharedFlow()

    fun onGetStartedTapped() {
        viewModelScope.launch {
            _effects.emit(OnboardingEffect.NavigateToTranslations)
        }
    }

    fun loadTranslations() {
        if (_uiState.value.loadingTranslations || _uiState.value.translations.isNotEmpty()) return

        viewModelScope.launch {
            _uiState.update { it.copy(loadingTranslations = true) }
            runCatching { repository.fetchTranslations() }
                .onSuccess { translations ->
                    val initialSelection = translations.firstOrNull()?.id?.let(::setOf).orEmpty()
                    _uiState.update {
                        it.copy(
                            loadingTranslations = false,
                            translations = translations,
                            selectedIds = initialSelection,
                        )
                    }
                }
                .onFailure {
                    val fallback = sampleTranslations
                    _uiState.update {
                        it.copy(
                            loadingTranslations = false,
                            translations = fallback,
                            selectedIds = setOf(fallback.first().id),
                        )
                    }
                }
        }
    }

    fun onToggleTranslation(id: String) {
        _uiState.update { state ->
            val updated = if (id in state.selectedIds) state.selectedIds - id else state.selectedIds + id
            state.copy(selectedIds = updated)
        }
    }

    fun onDownloadAndContinueTapped() {
        val currentState = _uiState.value
        if (currentState.selectedIds.isEmpty() || currentState.downloadingTranslations) return

        viewModelScope.launch {
            _uiState.update { it.copy(downloadingTranslations = true) }
            repository.downloadTranslations(currentState.selectedIds)
            onboardingStateStore.setOnboardingComplete(true)
            _uiState.update { it.copy(downloadingTranslations = false) }
            _effects.emit(OnboardingEffect.NavigateToHome)
        }
    }
}

sealed interface OnboardingEffect {
    data object NavigateToTranslations : OnboardingEffect
    data object NavigateToHome : OnboardingEffect
}

private val sampleTranslations = listOf(
    BibleTranslation("esv", "English", "ESV", "English Standard Version", 4.2),
    BibleTranslation("kjv", "Classical", "KJV", "King James Version", 3.8),
    BibleTranslation("niv", "Modern", "NIV", "New International Version", 4.5),
    BibleTranslation("nasb", "Literal", "NASB", "New American Standard Bible", 4.0),
)
