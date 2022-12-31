package com.example.functions

import com.example.models.Product
import com.example.models.Products
import com.example.models.isValid
import isValid
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

fun getProduct(id: Int): Product? {
    val product = transaction {
        Products.select { Products.id eq id }.map { Products.toProduct(it) }
    }
    return if (product.isEmpty()) null else product[0]
}

fun getAllProducts(): List<Product> {
    val products = transaction {
        Products.selectAll().map { Products.toProduct(it) }
    }
    return products
}

fun addProduct(product: Product) {
    if (!product.isValid()) return
    transaction {
        Products.insert {
            it[Products.name] = product.name
            it[Products.qty] = product.qty
            it[Products.price] = product.price
        }
    }
}

fun changeProduct(product: Product, id: Int) {
    transaction {
        Products.update({ Products.id eq id }) {
            it[Products.name] = product.name
            it[Products.qty] = product.qty
            it[Products.price] = product.price
        }
    }
}

fun deleteProduct(id: Int) {
    transaction {
        Products.deleteWhere { Products.id eq id }
    }
}