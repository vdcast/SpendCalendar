package com.vdcast.spendcalendar.categories.list

import com.vdcast.spendcalendar.base.BaseViewModel
import com.vdcast.spendcalendar.base.BaseViewState
import com.vdcast.spendcalendar.categories.CategoriesRepository
import com.vdcast.spendcalendar.categories.create.CreateCategoryData
import com.vdcast.spendcalendar.categories.create.toCategory
import com.vdcast.spendcalendar.categories.list.CategoriesViewModel.*
import com.vdcast.spendcalendar.categories.model.Category
import com.vdcast.spendcalendar.extensions.now
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.datetime.LocalDateTime

class CategoriesViewModel (
    private val repository: CategoriesRepository
) : BaseViewModel<State, Nothing>(){

    override fun initialState() = State.NONE

    init {
        activate()
    }

    private fun activate(){
        repository.getAllFlow().onEach {
            updateState { copy(categoties = it) }
        }.launchIn(viewModelScope)
    }

    fun createCategory(data: CreateCategoryData){
        val now = LocalDateTime.now()
        val category = data.toCategory(now)
        viewModelScope.launch {
            repository.create(category)
        }
    }

    data class State(
        val categoties: List<Category>
    ) : BaseViewState {

        companion object {
            val NONE = State(emptyList())
        }
    }
}