import com.example.models.Products
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDatabase() {
    transaction {
        SchemaUtils.create(Products)

        Products.insert {
            it[Products.name] = "Banana"
            it[Products.qty] = 100
            it[Products.price] = 243.00
            it[Products.type] = "Fruit"
        }

        Products.insert {
            it[Products.name] = "Potato"
            it[Products.qty] = 100
            it[Products.price] = 30.00
            it[Products.type] = "Vegetable"
        }

        Products.insert {
            it[Products.name] = "Popcorn"
            it[Products.qty] = 100
            it[Products.price] = 221.00
            it[Products.type] = "Snack"
        }

        Products.insert {
            it[Products.name] = "Cola"
            it[Products.qty] = 100
            it[Products.price] = 899.00
            it[Products.type] = "Drink"
        }

    }
}