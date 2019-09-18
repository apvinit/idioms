package xyz.codingabc.idioms.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import xyz.codingabc.idioms.data.model.Antonym

@Dao
interface AntonymDao {

    @Query("SELECT * FROM antonyms ORDER BY  word")
    fun getAll(): LiveData<List<Antonym>>

    @Query("SELECT * FROM antonyms WHERE antonym LIKE :keyword ORDER BY word")
    fun getBySearch(keyword: String): LiveData<List<Antonym>>

}