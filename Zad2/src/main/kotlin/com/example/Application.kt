package com.example

import com.example.models.Product
import com.example.models.User
import com.example.plugins.*
import com.google.api.services.storage.model.Notifications
import config.firebase.FirebaseAdmin
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.apache.http.client.methods.RequestBuilder.head
import org.slf4j.event.Level
import com.pusher.pushnotifications.PushNotifications;

fun main(args: Array<String>): Unit = io.ktor.server.jetty.EngineMain.main(args)

fun Application.main() {
    FirebaseAdmin.init()

    install(ContentNegotiation) { gson { setPrettyPrinting() } }

    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }

    routing {
        get("/") {
            call.respond(HttpStatusCode.OK, "I'm working just fine, thanks!")
        }

        authenticate {
            get("/authenticated") {
                call.respond(HttpStatusCode.OK, "My name is ${call.principal<User>()?.name}, and I'm authenticated!")
            }
        }
    }
    configureSecurity()
    configureHTTP()
    configureMonitoring()
    configureSerialization()
    configureRouting()

    val client = HttpClient() {
        install(Notifications) {
            onSuccess { call ->
                val product = call.response.receive<Product>()
            }
        }
    }
    val newProducts = client.get<List<Product>>("https://your-server.com/new-products")
    for (product in newProducts) {
        PushNotifications.start(getApplicationContext(), "Nowy Produkt: $product");
    }
    client.close()
}

