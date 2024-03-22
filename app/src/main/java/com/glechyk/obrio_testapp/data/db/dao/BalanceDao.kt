package com.glechyk.obrio_testapp.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.glechyk.obrio_testapp.data.db.entity.BalanceEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BalanceDao {

    @Upsert
    suspend fun upsertBalance(balanceEntity: BalanceEntity)

    @Query("SELECT * FROM balance WHERE id = 1")
    fun getSingleEntity(): Flow<BalanceEntity?>
}