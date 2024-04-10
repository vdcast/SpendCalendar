package com.vdcast.spend_calendar.root.model

import com.vdcast.spend_calendar.MR
import dev.icerock.moko.resources.ImageResource
import dev.icerock.moko.resources.StringResource

data class BottomBarItem(
    val title: StringResource,
    val appTab: AppTab,
    val icon: ImageResource
) {
    companion object {
        fun getItems() = listOf(
            BottomBarItem(MR.strings.events, AppTab.Events, MR.images.ic_calendar),
            BottomBarItem(MR.strings.categories, AppTab.Categories, MR.images.ic_categories),
            BottomBarItem(MR.strings.settings, AppTab.Settings, MR.images.ic_settings),
        )
    }
}
