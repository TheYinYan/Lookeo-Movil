package com.alanturing.lookeo_movil.core.data.local

import androidx.room3.ColumnTypeConverter
import java.util.Date


class DateConverter() {

    @ColumnTypeConverter
    fun fromDate(date: Date): Long {
        return date.time
    }

    @ColumnTypeConverter
    fun toDate(time: Long): Date {
        return Date(time)
    }
}