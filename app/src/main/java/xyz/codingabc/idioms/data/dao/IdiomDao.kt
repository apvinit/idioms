package xyz.codingabc.idioms.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import xyz.codingabc.idioms.data.model.Idiom

@Dao
interface IdiomDao {

    @Query("SELECT * FROM idiom")
    fun getAll() : LiveData<List<Idiom>>

    @Insert
    fun insertData(idioms: List<Idiom>)

}