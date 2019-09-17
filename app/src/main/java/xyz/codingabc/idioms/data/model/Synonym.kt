package xyz.codingabc.idioms.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "synonyms")
data class Synonym(
    @PrimaryKey val _id: String,
    @ColumnInfo(name = "word") val word: String,
    @ColumnInfo(name = "meaning") val meaning: String,
    @ColumnInfo(name = "synonym") val antonym: String
)