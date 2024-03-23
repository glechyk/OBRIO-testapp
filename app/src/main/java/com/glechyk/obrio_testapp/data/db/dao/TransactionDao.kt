package com.glechyk.obrio_testapp.data.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.glechyk.obrio_testapp.data.db.entity.TransactionEntity

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransactionEntity(transactionEntity: TransactionEntity)

    @Query("SELECT * FROM transactions ORDER BY time DESC")
    fun getTransactionEntities(): PagingSource<Int, TransactionEntity>
}