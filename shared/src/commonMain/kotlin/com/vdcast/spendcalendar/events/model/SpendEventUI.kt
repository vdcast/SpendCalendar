package com.vdcast.spendcalendar.events.model

import com.vdcast.spendcalendar.categories.model.Category

data class SpendEventUI(
    val id: String,
    val category: Category,
    val title: String,
    val cost: Double
)