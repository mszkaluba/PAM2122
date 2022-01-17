package com.project.checkyourenglish

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RsultsDao {
    @Insert
    fun insertResults(results: Results)

    @Query("select * from results")
    fun getAll(): List<Results>

}