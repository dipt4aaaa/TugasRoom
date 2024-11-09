package com.example.inventory.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import androidx.room.Delete
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    /**
     * Menyisipkan item baru ke dalam tabel `items`.
     * Jika terdapat item dengan ID yang sama, maka item baru akan diabaikan.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    /**
     * Memperbarui item yang sudah ada di dalam tabel `items`.
     */
    @Update
    suspend fun update(item: Item)

    /**
     * Menghapus item tertentu dari tabel `items`.
     */
    @Delete
    suspend fun delete(item: Item)

    /**
     * Mengambil item dari tabel `items` berdasarkan ID-nya.
     * Menggunakan Flow untuk mendapatkan pembaruan data secara real-time.
     */
    @Query("SELECT * from items WHERE id = :id")
    fun getItem(id: Int): Flow<Item>

    /**
     * Mengambil semua item dari tabel `items` dan mengurutkannya berdasarkan nama (ASC).
     * Menggunakan Flow untuk mendapatkan pembaruan data secara real-time.
     */
    @Query("SELECT * from items ORDER BY name ASC")
    fun getAllItems(): Flow<List<Item>>
}
