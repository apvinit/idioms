package xyz.codingabc.idioms.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import xyz.codingabc.idioms.data.model.Synonym

@Dao
interface SynonymDao {
    @Query("SELECT * FROM synonyms ORDER BY word")
    fun getAll(): LiveData<List<Synonym>>

    @Query("SELECT * FROM synonyms WHERE word LIKE :keyword ORDER BY word")
    fun getBySearch(keyword: String): LiveData<List<Synonym>>

}