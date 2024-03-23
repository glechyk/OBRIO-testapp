package com.glechyk.obrio_testapp.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.glechyk.obrio_testapp.data.db.entity.BalanceEntity
import com.glechyk.obrio_testapp.utils.DB_SINGLE_FIELD_ID
import kotlinx.coroutines.flow.Flow

@Dao
interface BalanceDao {

    @Upsert
    suspend fun upsertBalanceEntity(balanceEntity: BalanceEntity)

    @Query("SELECT * FROM balance WHERE id = $DB_SINGLE_FIELD_ID")
    fun getBalanceEntity(): Flow<BalanceEntity?>
}