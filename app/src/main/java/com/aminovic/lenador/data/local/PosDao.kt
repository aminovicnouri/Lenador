package com.aminovic.lenador.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aminovic.lenador.data.local.entities.ORDERS_TABLE_NAME
import com.aminovic.lenador.data.local.entities.OrderEntity
import com.aminovic.lenador.data.local.entities.PRODUCTS_TABLE_NAME
import com.aminovic.lenador.data.local.entities.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PosDao {
    /** Insert product */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(productEntity: ProductEntity)

    /** Get all Products */
    @Query("SELECT * FROM $PRODUCTS_TABLE_NAME")
    fun getProducts(): Flow<List<ProductEntity>>

    /** delete product */
    @Delete
    suspend fun deleteProduct(productEntity: ProductEntity)


    @Query("DELETE FROM $PRODUCTS_TABLE_NAME")
    suspend fun clearProducts()

    @Query("SELECT * FROM $PRODUCTS_TABLE_NAME WHERE id = :id")
    suspend fun getProductById(id: Int): ProductEntity?


    /** Insert order */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(orderEntity: OrderEntity)

    /** Get all order */
    @Query("SELECT * FROM $ORDERS_TABLE_NAME")
    fun getOrders(): Flow<List<OrderEntity>>

    /** delete order */
    @Delete
    suspend fun deleteOrder(orderEntity: OrderEntity)


    @Query("DELETE FROM $ORDERS_TABLE_NAME")
    suspend fun clearOrders()

    @Query("SELECT * FROM $ORDERS_TABLE_NAME WHERE id = :id")
    suspend fun getOrderById(id: Int): OrderEntity?

}