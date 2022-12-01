package com.example


import com.example.plugins.*
import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import configureDatabase
import io.ktor.server.application.*
import org.jetbrains.exposed.*
import org.jetbrains.exposed.sql.Database

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused")
fun Application.module() {
    Database.connect("jdbc:sqlite:./identifier.sqlite", "org.sqlite.JDBC")
    configureRouting()
    configureSerialization()
    configureDatabase()
}
