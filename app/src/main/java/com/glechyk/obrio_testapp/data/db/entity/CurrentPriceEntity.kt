package com.glechyk.obrio_testapp.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currentPrice")
data class CurrentPriceEntity(
    @PrimaryKey
    val id: Int = 1,
    val amount: String,
    val time: String,
)
