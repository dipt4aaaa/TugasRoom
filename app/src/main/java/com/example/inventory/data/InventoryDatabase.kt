package com.example.inventory.data

import androidx.room.Database
import androidx.room.RoomDatabase
import android.content.Context
import androidx.room.Room

/**
 * InventoryDatabase adalah database Room untuk aplikasi ini.
 * Database ini memiliki satu tabel, yaitu tabel `items`, yang didefinisikan oleh data class `Item`.
 */
@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class InventoryDatabase : RoomDatabase() {

    // Menyediakan akses ke DAO (Data Access Object) untuk tabel `items`.
    abstract fun itemDao(): ItemDao

    companion object {
        // Menyimpan instance tunggal dari InventoryDatabase untuk menghindari pembuatan instance berulang.
        @Volatile
        private var Instance: InventoryDatabase? = null

        /**
         * Mengambil instance dari InventoryDatabase.
         * Jika belum ada, akan membuat database baru menggunakan databaseBuilder.
         */
        fun getDatabase(context: Context): InventoryDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, InventoryDatabase::class.java, "item_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
