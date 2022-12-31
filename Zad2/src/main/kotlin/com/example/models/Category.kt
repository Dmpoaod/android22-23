import com.example.models.Product
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.Table

data class Category(
    val id: Int? = null,
    val name: String,
)

fun Product.isValid(): Boolean {
    return this.name.isNotEmpty() && this.id != null
}

object Products : Table() {
    val id: Column<Int> = integer("id").autoIncrement()
    val name: Column<String> = varchar("name", 255)

    override val primaryKey = PrimaryKey(id, name="PK_Cat_ID")

}
