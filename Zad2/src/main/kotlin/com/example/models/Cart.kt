package com.example.models

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

data class Cart(
    val id : Int? = null,
    val item : Int,
    val userId : Int
)

object Carts : Table() {
    val id: Column<Int> = integer("id").autoIncrement()
    val item: Column<Int> = integer("item")
    val userId: Column<Int> = integer("userId")

    override val primaryKey = PrimaryKey(id, name = "PK_User_ID")

    fun toCart(row : ResultRow): Cart =
        Cart (
            id = row[Carts.id],
            item = row[Carts.item],
            userId = row[Carts.userId]
        )
}