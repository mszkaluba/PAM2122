package com.project.checkyourenglish

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Results::class), version = 3)
abstract class AppDataBase : RoomDatabase() {
    abstract fun result(): RsultsDao
}
