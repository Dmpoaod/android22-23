package com.example.functions

import com.example.models.Cart
import com.example.models.Carts
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

fun getCart(id: Int): Cart? {
    val basket = transaction {
        Carts.select { Carts.id eq id }.map { Carts.toCart(it) }
    }
    return if (basket.isEmpty()) null else basket[0]
}

fun getAllCarts(): List<Cart> {
    val baskets = transaction {
        Carts.selectAll().map { Carts.toCart(it) }
    }
    return baskets
}

fun addCart(basket: Cart) {
    transaction {
        Carts.insert {
            it[Carts.item] = item
            it[Carts.userId] = userId
        }
    }
}

fun changeCart(basket: Cart, id: Int) {
    transaction {
        Carts.update({ Carts.id eq id }) {
            it[Carts.item] = item
            it[Carts.userId] = userId
        }
    }
}

fun deleteCart(id: Int) {
    transaction {
        Carts.deleteWhere { Carts.id eq id }
    }
}