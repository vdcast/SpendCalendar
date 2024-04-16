package com.vdcast.spendcalendar.platform

import java.util.UUID

actual fun randomUUID(): String = UUID.randomUUID().toString()