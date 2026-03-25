package com.blazesoftstudio.biblereader.core.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore by preferencesDataStore(name = "bible_reader_preferences")
private val onboardingCompleteKey = booleanPreferencesKey("onboarding_complete")

class AndroidOnboardingStateStore(
    private val context: Context,
) : OnboardingStateStore {
    override val onboardingComplete: Flow<Boolean> =
        context.dataStore.data.map { preferences ->
            preferences[onboardingCompleteKey] ?: false
        }

    override suspend fun setOnboardingComplete(completed: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[onboardingCompleteKey] = completed
        }
    }
}
