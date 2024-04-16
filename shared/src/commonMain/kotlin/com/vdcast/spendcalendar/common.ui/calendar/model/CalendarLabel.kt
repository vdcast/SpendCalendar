package com.vdcast.spendcalendar.common.ui.calendar.model

import kotlinx.datetime.LocalDate

data class CalendarLabel(
    val id: String,
    val colorHex: String?,
    val localDate: LocalDate
)