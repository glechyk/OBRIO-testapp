package com.glechyk.obrio_testapp.data.db.converter

import androidx.room.TypeConverter
import com.glechyk.obrio_testapp.domain.model.Category

class CategoryConverter {
    @TypeConverter
    fun fromCategory(category: Category?): String? {
        return category?.value
    }

    @TypeConverter
    fun toCategory(categoryName: String?): Category? {
        return categoryName?.let {
            when (categoryName) {
                Category.GROCERIES.value -> Category.GROCERIES
                Category.TAXI.value -> Category.TAXI
                Category.ELECTRONICS.value -> Category.ELECTRONICS
                Category.RESTAURANT.value -> Category.RESTAURANT
                else -> Category.OTHER
            }
        }
    }
}