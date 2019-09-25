package xyz.codingabc.idioms.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "prepositions")
data class Preposition(
    @PrimaryKey val _id: String,
    @ColumnInfo(name = "word") val word: String,
    @ColumnInfo(name = "preposition") val preposition: String,
    @ColumnInfo(name = "nouns") val nouns: String
)