import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoDatabase

lateinit var client: MongoClient
lateinit var database: MongoDatabase

const val connectionString = "mongodb://localhost:27017/test"

fun connect() {
	client = MongoClient.create(connectionString)
	database = client.getDatabase("test")
}
