package com.glechyk.obrio_testapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.glechyk.obrio_testapp.data.db.converter.CategoryConverter
import com.glechyk.obrio_testapp.data.db.dao.BalanceDao
import com.glechyk.obrio_testapp.data.db.dao.CurrentPriceDao
import com.glechyk.obrio_testapp.data.db.dao.TransactionDao
import com.glechyk.obrio_testapp.data.db.entity.BalanceEntity
import com.glechyk.obrio_testapp.data.db.entity.CurrentPriceEntity
import com.glechyk.obrio_testapp.data.db.entity.TransactionEntity

@Database(
    entities = [BalanceEntity::class, CurrentPriceEntity::class, TransactionEntity::class],
    version = 1,
)
@TypeConverters(CategoryConverter::class)
abstract class BitcoinsDatabase : RoomDatabase() {

    abstract val balanceDao: BalanceDao

    abstract val currentPriceDao: CurrentPriceDao

    abstract val transactionDao: TransactionDao
}