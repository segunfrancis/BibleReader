package com.blazesoftstudio.biblereader.core.data

import kotlinx.coroutines.flow.Flow

interface OnboardingStateStore {
    val onboardingComplete: Flow<Boolean>
    suspend fun setOnboardingComplete(completed: Boolean)
}
