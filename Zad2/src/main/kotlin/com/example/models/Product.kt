package com.example.models


import com.google.api.client.json.Json
import com.google.common.net.MediaType.parse
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table


data class Product(
    val id: Int? = null,
    val name: String,
    val qty: Int,
    val price: Double,
    val type: String
)


object Products : Table() {
    val id: Column<Int> = integer("id").autoIncrement()
    val name: Column<String> = varchar("name", 255)
    val qty: Column<Int> = integer("qty")
    val price: Column<Double> = double("price")
    val type: Column<String> = text("type")

    override val primaryKey = PrimaryKey(id, name="PK_Product_ID")

    fun toProduct(row : ResultRow): Product =
        Product (
            id = row[Products.id],
            name = row[Products.name],
            qty = row[Products.qty],
            price = row[Products.price],
            type = row[Products.type]
        )

}

fun parseProduct(json: String): Product {
    val jsonObject = Json.parse(json).jsonObject
    val product = Product(
        id = jsonObject["id"].Int,
        name = jsonObject["name"].content,
        price = jsonObject["price"].double,
        type = jsonObject["type"].String,
        qty = jsonObject["qty"].Int
    )
    return product
}

suspend fun downloadProduct(productId: String?): Product {
    val client = HttpClient()
    val url = "https://yourserver.com/products/$productId"
    val response = client.get<HttpResponse>(url)
    val json = response.readText()
    client.close()
    return parseProduct(json)
}

