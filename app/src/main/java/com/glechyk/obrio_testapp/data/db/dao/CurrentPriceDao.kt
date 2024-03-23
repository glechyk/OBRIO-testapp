package com.glechyk.obrio_testapp.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.glechyk.obrio_testapp.data.db.entity.CurrentPriceEntity
import com.glechyk.obrio_testapp.utils.DB_SINGLE_FIELD_ID
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrentPriceDao {

    @Upsert
    suspend fun upsertCurrentPrice(currentPriceEntity: CurrentPriceEntity)

    @Query("SELECT * FROM currentPrice WHERE id = $DB_SINGLE_FIELD_ID")
    fun getCurrentPriceEntity(): Flow<CurrentPriceEntity?>
}