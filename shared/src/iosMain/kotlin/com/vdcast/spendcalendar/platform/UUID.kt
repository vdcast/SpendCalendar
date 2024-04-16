package com.vdcast.spendcalendar.platform

import platform.Foundation.NSUUID

actual fun randomUUID() = NSUUID().UUIDString()