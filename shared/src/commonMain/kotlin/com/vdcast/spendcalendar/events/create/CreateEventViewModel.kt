package com.vdcast.spendcalendar.events.create

import com.vdcast.spendcalendar.base.BaseEvent
import com.vdcast.spendcalendar.base.BaseViewModel
import com.vdcast.spendcalendar.base.BaseViewState
import com.vdcast.spendcalendar.categories.model.Category
import com.vdcast.spendcalendar.events.create.CreateEventViewModel.*
import com.vdcast.spendcalendar.events.model.SpendEvent
import com.vdcast.spendcalendar.extensions.now
import com.vdcast.spendcalendar.platform.randomUUID
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime

class CreateEventViewModel : BaseViewModel<State, Event>() {

    override fun initialState() = State.NONE

    fun selectDate(date: LocalDate?) = updateState { copy(date = date ?: LocalDate.now()) }
    fun resetState() = updateState { State.NONE }
    fun changeTitle(title: String) = updateState { copy(title = title) }
    fun changeNote(note: String) = updateState { copy(note = note) }
    fun changeCost(cost: String) = updateState { copy(cost = cost.toDoubleOrNull() ?: this.cost) }
    fun selectCategory(category: Category) = updateState { copy(category = category) }

    fun finish() {
        val spendEvent = with(state.value){
            val now = LocalDateTime.now()
            SpendEvent(
                id = randomUUID(),
                title = title,
                cost = cost,
                date = date,
                categoryId = category.id,
                createdAt = now,
                updatedAt = now,
                note = note
            )
        }
        resetState()
        pushEvent(Event.Finish(spendEvent))
    }


    data class State(
        val title: String,
        val category: Category,
        val date: LocalDate,
        val cost: Double,
        val note: String
    ) : BaseViewState {
        companion object {
            val NONE = State(
                title = "",
                category = Category.NONE,
                date = LocalDate.now(),
                cost = 0.0,
                note = ""
            )
        }
    }

    sealed interface Event : BaseEvent {
        data class Finish(val spendEvent: SpendEvent) : Event
    }


}