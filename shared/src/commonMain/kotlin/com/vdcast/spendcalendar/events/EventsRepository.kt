package com.vdcast.spendcalendar.events

import com.vdcast.spendcalendar.events.model.SpendEvent
import com.vdcast.spendcalendar.events.model.SpendEventDao

class EventsRepository(
    private val dao: SpendEventDao
) {

    fun getAllFlow() = dao.getAllFlow()

    suspend fun getAll() = dao.getAll()

    suspend fun insertAll(events: List<SpendEvent>) = dao.insertAll(events)

    suspend fun create(spendEvent: SpendEvent) = dao.insert(spendEvent)
}