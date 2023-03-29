package com.publicissapient.stepchallenge.domain.util

import java.util.*
import java.text.SimpleDateFormat
import java.util.Date

object Utilities {
    fun generateId(prefix: String = ""): String {
        return prefix+UUID.randomUUID().toString()
    }

    fun formatDate(date: Date, format: String): String {
        val format = SimpleDateFormat(format)
        return format.format(date)
    }
}