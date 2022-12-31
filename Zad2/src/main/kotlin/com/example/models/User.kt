package com.example.models

import io.ktor.server.auth.*
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

data class User(
    val id: String = null,
    val name: String,
    val password: String
): Principal

fun User.isValid(): Boolean {
    return !this.name.isEmpty() &&
            !this.password.isEmpty()
}

object Users : Table() {
    val id: Column<Int> = integer("id").autoIncrement()
    val name: Column<String> = varchar("name", 255)
    val password: Column<String> = varchar("password", 255)

    override val primaryKey = PrimaryKey(id, name="PK_User_ID")

    fun toUser(row : ResultRow): User =
        User (
            id = row[Users.id],
            name = row[Users.name],
            password = row[Users.password]
        )

}