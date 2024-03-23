package com.glechyk.obrio_testapp.utils

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun Long.toParsedDate(): String = Instant
    .fromEpochMilliseconds(this)
    .toLocalDateTime(TimeZone.UTC)
    .let { "${it.date} ${it.time}" }