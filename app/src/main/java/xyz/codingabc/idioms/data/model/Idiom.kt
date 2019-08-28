package xyz.codingabc.idioms.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "idioms")
data class Idiom(
    @PrimaryKey val _id: String,
    @ColumnInfo(name = "idiom") val text: String,
    @ColumnInfo(name = "meaning") val meaning: String)