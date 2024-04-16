@file:OptIn(ExperimentalMaterialApi::class)

package com.vdcast.spendcalendar.events.list.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.zIndex
import com.vdcast.spendcalendar.common.ui.atoms.FAB
import com.vdcast.spendcalendar.common.ui.atoms.RootBox
import com.vdcast.spendcalendar.common.ui.calendar.compose.CalendarColors
import com.vdcast.spendcalendar.common.ui.calendar.compose.DatePickerView
import com.vdcast.spendcalendar.common.ui.theme.AppThemeProvider
import com.vdcast.spendcalendar.di.DatePickerFactoryQualifier
import com.vdcast.spendcalendar.di.DatePickerSingleQualifier
import com.vdcast.spendcalendar.di.getKoinInstance
import com.vdcast.spendcalendar.events.create.compose.CreateEventView
import com.vdcast.spendcalendar.events.list.EventsViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun EventsScreen(
    viewModel: EventsViewModel
) {

    val sheetState =
        rememberModalBottomSheetState(ModalBottomSheetValue.Hidden, skipHalfExpanded = true)
    val scope = rememberCoroutineScope()
    val state by viewModel.state.collectAsState()

    ModalBottomSheetLayout(
        sheetContent = {
            CreateEventView(
                isExpand = sheetState.currentValue == ModalBottomSheetValue.Expanded,
                selectedDay = state.selectedDay,
                viewModel = getKoinInstance()
            ){ newEvent ->
                viewModel.createEvent(newEvent)
                scope.launch { sheetState.hide() }
            }
        },
        sheetState = sheetState,
        sheetBackgroundColor = Color.Transparent,
        modifier = Modifier.zIndex(1f)
    ) {
        RootBox {
            Column {
                DatePickerView(
                    viewModel = getKoinInstance(DatePickerSingleQualifier),
                    colors = CalendarColors.default.copy(
                        colorSurface = AppThemeProvider.colors.surface,
                        colorOnSurface = AppThemeProvider.colors.onSurface,
                        colorAccent = AppThemeProvider.colors.accent
                    ),
                    firstDayIsMonday = AppThemeProvider.appPrefs.firstDayIsMonday,
                    labels = state.calendarLabels,
                    selectDayListener = viewModel::selectDay
                )

                LazyColumn(modifier = Modifier.weight(1f)) {
                    items(state.eventsByDay) { iventUI ->
                        SpendEventItem(iventUI)
                    }
                }
            }

            FAB { scope.launch { sheetState.show() } }
        }
    }

}