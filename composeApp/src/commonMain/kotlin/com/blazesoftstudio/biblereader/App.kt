package com.blazesoftstudio.biblereader

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.blazesoftstudio.biblereader.core.data.OnboardingStateStore
import com.blazesoftstudio.biblereader.core.navigation.HydroHeroNavigation
import com.blazesoftstudio.biblereader.designsystem.BibleReaderTheme
import com.blazesoftstudio.biblereader.feature.home.HomeScreen
import com.blazesoftstudio.biblereader.feature.onboarding.OnboardingEffect
import com.blazesoftstudio.biblereader.feature.onboarding.OnboardingScreen
import com.blazesoftstudio.biblereader.feature.onboarding.OnboardingViewModel
import com.blazesoftstudio.biblereader.feature.onboarding.TranslationSelectionScreen
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    BibleReaderTheme {
        val onboardingStateStore = koinInject<OnboardingStateStore>()
        val viewModel = koinViewModel<OnboardingViewModel>()
        val uiState by viewModel.uiState.collectAsState()
        val onboardingComplete by onboardingStateStore.onboardingComplete.collectAsState(initial = null)
        val navController = rememberNavController()

        LaunchedEffect(Unit) {
            viewModel.effects.collect { effect ->
                when (effect) {
                    OnboardingEffect.NavigateToTranslations -> navController.navigate(HydroHeroNavigation.TranslationSelection)
                    OnboardingEffect.NavigateToHome -> {
                        navController.navigate(HydroHeroNavigation.Home) {
                            popUpTo<HydroHeroNavigation.Onboarding> { inclusive = true }
                        }
                    }
                }
            }
        }

        if (onboardingComplete == null) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
            return@BibleReaderTheme
        }

        NavHost(
            navController = navController,
            startDestination = if (onboardingComplete == true) HydroHeroNavigation.Home else HydroHeroNavigation.Onboarding,
        ) {
            composable<HydroHeroNavigation.Onboarding> {
                OnboardingScreen(onGetStarted = viewModel::onGetStartedTapped)
            }

            composable<HydroHeroNavigation.TranslationSelection> {
                LaunchedEffect(Unit) {
                    viewModel.loadTranslations()
                }
                TranslationSelectionScreen(
                    uiState = uiState,
                    onTranslationToggle = viewModel::onToggleTranslation,
                    onDownloadAndContinue = viewModel::onDownloadAndContinueTapped,
                )
            }

            composable<HydroHeroNavigation.Home> {
                HomeScreen()
            }
        }
    }
}
