package com.vdcast.spend_calendar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

fun sayHello() {
    println("Hello from common code 😀")
}

@Composable
fun SayHelloFromCommon() {
    Box(
        modifier = Modifier
            .size(200.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Hello from Compose common"
        )
    }
}