package com.glechyk.obrio_testapp.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "balance")
data class BalanceEntity(
    @PrimaryKey
    val id: Int = 1,
    val amount: Double,
)
