package xyz.codingabc.idioms.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import xyz.codingabc.idioms.data.model.Idiom

@Dao
interface IdiomDao {

    @Query("SELECT * FROM idioms")
    fun getAll(): LiveData<List<Idiom>>

    @Query("SELECT * FROM idioms WHERE _id LIKE :id")
    fun getById(id: String): LiveData<Idiom>

    @Query("SELECT * FROM idioms WHERE idiom LIKE  :keyword or meaning LIKE :keyword")
    fun getBySearch(keyword: String) : LiveData<List<Idiom>>

    @Insert
    fun insertData(idioms: List<Idiom>)

}