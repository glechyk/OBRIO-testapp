package com.glechyk.obrio_testapp.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.glechyk.obrio_testapp.data.db.entity.CurrentPriceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrentPriceDao {

    @Upsert
    suspend fun upsertCurrentPrice(currentPriceEntity: CurrentPriceEntity)

    @Query("SELECT * FROM currentPrice WHERE id = 1")
    fun getSingleEntity(): Flow<CurrentPriceEntity?>
}