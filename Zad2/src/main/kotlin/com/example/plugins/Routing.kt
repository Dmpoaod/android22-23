
package com.example.plugins

import com.example.models.Product
import com.google.gson.Gson
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Application.configureRouting() {

    routing {
        get("/") {
            val x : List<Product> = listOf(
                Product(1, "Product 1", 5, 12.45, "test"),
                Product(2, "Product 2", 10, 32.56, "test 2")
            )
            var gson = Gson()
            call.respondText(gson.toJson(x))
        }
    }
}