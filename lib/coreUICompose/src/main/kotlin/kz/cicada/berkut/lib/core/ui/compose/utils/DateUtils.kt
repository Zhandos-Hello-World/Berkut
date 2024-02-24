package kz.cicada.berkut.lib.core.ui.compose.utils

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

const val DD_MM_YYYY = "dd.MM.yyyy"

fun Long.toTimeDateString(pattern: String = DD_MM_YYYY): String =
    SimpleDateFormat(pattern, Locale.getDefault()).format(Date(this))

fun String.toLocalDate(pattern: String = DD_MM_YYYY): LocalDate =
    LocalDate.parse(this, DateTimeFormatter.ofPattern(pattern))

fun LocalDate.getMillis(zoneId: ZoneId = ZoneId.systemDefault()): Long =
    this.atStartOfDay(zoneId).toInstant().toEpochMilli()