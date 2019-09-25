package xyz.codingabc.idioms.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import xyz.codingabc.idioms.data.model.Preposition

@Dao
interface PrepositionDao {
    @Query("SELECT * FROM prepositions")
    fun getAll(): LiveData<List<Preposition>>

    @Query("SELECT * FROM prepositions WHERE word LIKE :keyword")
    fun getBySearch(keyword: String): LiveData<List<Preposition>>
}