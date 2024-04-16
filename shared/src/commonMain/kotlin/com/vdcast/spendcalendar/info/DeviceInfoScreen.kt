package com.vdcast.spendcalendar.info

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.vdcast.spendcalendar.platform.DeviceInfo

@Composable
fun DeviceInfoScreen() {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        Text(DeviceInfo().getSummary())
    }
}