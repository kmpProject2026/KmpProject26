package com.itis.kmpproj26.core.util.date

import kotlinx.datetime.*
import kotlin.time.Clock
import kotlin.time.Instant

object DateHelper {

    private const val DATE_FORMAT = "dd.MM.yyyy"
    private const val DATETIME_FORMAT = "dd.MM.yyyy HH:mm"
    private const val TIME_FORMAT = "HH:mm"

    // ---------- NOW ----------

    fun getTodayDisplayFormat(): String {
        val now = Clock.System.now()
        val local = now.toLocalDateTime(TimeZone.currentSystemDefault())
        return formatDate(local.date)
    }

    fun getCurrentTimeDisplayFormat(): String {
        val now = Clock.System.now()
        val local = now.toLocalDateTime(TimeZone.currentSystemDefault())
        return formatTime(local)
    }

    // ---------- FORMAT ----------

    fun formatDate(date: LocalDate): String {
        return buildString {
            append(date.dayOfMonth.toString().padStart(2, '0'))
            append(".")
            append(date.monthNumber.toString().padStart(2, '0'))
            append(".")
            append(date.year)
        }
    }

    fun formatTime(dateTime: LocalDateTime): String {
        return buildString {
            append(dateTime.hour.toString().padStart(2, '0'))
            append(":")
            append(dateTime.minute.toString().padStart(2, '0'))
        }
    }

    fun formatDateTime(dateTime: LocalDateTime): String {
        return "${formatDate(dateTime.date)} ${formatTime(dateTime)}"
    }

    // ---------- PARSE ----------

    fun parseDisplayDate(displayDateString: String): LocalDate {
        val parts = displayDateString.split(".")
        return LocalDate(
            year = parts[2].toInt(),
            monthNumber = parts[1].toInt(),
            dayOfMonth = parts[0].toInt()
        )
    }

    fun parseDisplayTime(displayTimeString: String): LocalTime {
        val parts = displayTimeString.split(":")
        return LocalTime(
            hour = parts[0].toInt(),
            minute = parts[1].toInt()
        )
    }

    fun parseDisplayDateAndTime(
        dateString: String,
        timeString: String
    ): LocalDateTime {
        val date = parseDisplayDate(dateString)
        val time = parseDisplayTime(timeString)

        return LocalDateTime(
            year = date.year,
            monthNumber = date.monthNumber,
            dayOfMonth = date.dayOfMonth,
            hour = time.hour,
            minute = time.minute
        )
    }

    fun fromMillis(millis: Long): LocalDateTime {
        val instant = Instant.fromEpochMilliseconds(millis)
        return instant.toLocalDateTime(TimeZone.currentSystemDefault())
    }
}