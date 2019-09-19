package xyz.codingabc.idioms.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import xyz.codingabc.idioms.data.model.OneWordSubstitution

@Dao
interface OneWordSubstitutionDao {

    @Query("SELECT * FROM onewordsubstitutions")
    fun getAll(): LiveData<List<OneWordSubstitution>>

    @Query("SELECT * FROM onewordsubstitutions WHERE word LIKE :keyword")
    fun getBySearch(keyword: String): LiveData<List<OneWordSubstitution>>

}