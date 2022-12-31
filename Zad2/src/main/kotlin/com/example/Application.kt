package com.example

import com.example.models.User
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.jetty.*
import com.example.plugins.*
import config.firebase.FirebaseAdmin
import io.ktor.http.*
import io.ktor.serialization.gson.*
import io.ktor.server.auth.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.slf4j.event.Level

fun main(args: Array<String>): Unit = io.ktor.server.jetty.EngineMain.main(args)

fun Application.module() {
    // initialize Firebase Admin SDK
    FirebaseAdmin.init()

    install(ContentNegotiation) { gson { setPrettyPrinting() } }
    install(Authentication) { firebase { configure() } }
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
}
