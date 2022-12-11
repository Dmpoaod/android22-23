import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

data class Cathegory(
    val id : Int? = null,
    val name : String,
    )

fun Product.isValid(): Boolean {
    return !this.name.isEmpty() &&
        !this.id.isEmpty()
}

object Products : Table() {
    val id: Column<Int> = integer("id").autoIncrement()
    val name: Column<String> = varchar("name", 255)

    override val primaryKey = PrimaryKey(id, name="PK_Cat_ID")

    fun toProduct(row : ResultRow): Product = Product (id = row[Products.id],name = row[Products.name])

}
