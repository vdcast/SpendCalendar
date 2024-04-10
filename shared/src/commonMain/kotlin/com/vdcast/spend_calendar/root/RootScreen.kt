package com.vdcast.spend_calendar.root

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.vdcast.spend_calendar.common.ui.AppTheme
import com.vdcast.spend_calendar.common.ui.AppThemeProvider
import com.vdcast.spend_calendar.settings.SettingsViewModel
import com.vdcast.spend_calendar.settings.compose.SettingsScreen

@Composable
fun RootScreen(viewModel: RootViewModel) {

    val state by viewModel.state.collectAsState()

    AppTheme(
        themeIsDark = state.themeIsDark,
        appPrefs = state.appPrefs
    ) {
        Box(
            modifier = Modifier.fillMaxSize().background(AppThemeProvider.colors.background)
        ) {

            SettingsScreen(SettingsViewModel())
        }
    }
//    DeviceInfoScreen()
}