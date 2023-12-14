package com.aminovic.lenador.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aminovic.lenador.data.local.entities.ProductEntity

@Database(
    entities = [ProductEntity::class, ],
    version = 1,
)
abstract class PosDatabase : RoomDatabase() {
    abstract val posDao: PosDao
}
