package com.vdcast.spend_calendar.root.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.vdcast.spend_calendar.categories.CategoriesScreen
import com.vdcast.spend_calendar.common.ui.AppTheme
import com.vdcast.spend_calendar.common.ui.AppThemeProvider
import com.vdcast.spend_calendar.events.EventsScreen
import com.vdcast.spend_calendar.root.RootViewModel
import com.vdcast.spend_calendar.root.model.AppTab
import com.vdcast.spend_calendar.settings.SettingsViewModel
import com.vdcast.spend_calendar.settings.compose.SettingsScreen

@Composable
fun RootScreen(viewModel: RootViewModel) {

    val state by viewModel.state.collectAsState()

    AppTheme(state.themeIsDark, state.appPrefs) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(AppThemeProvider.colors.background)
        ) {

            RootNavigation(state.selectedTab)
            RootBottomBar(state.selectedTab) { tab ->
                viewModel.handleClickOnTab(tab)
            }
        }
    }
}

@Composable
fun BoxScope.RootNavigation(selectedTab: AppTab){

    when(selectedTab){
        AppTab.Categories -> CategoriesScreen()
        AppTab.Events -> EventsScreen()
        AppTab.Settings -> SettingsScreen(SettingsViewModel())
    }

}