import com.mongodb.client.model.Filters
import com.mongodb.client.model.Updates
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import kotlinx.coroutines.flow.toList

const val collectionName = "users"

suspend fun createCollection(database: MongoDatabase) {
	if (collectionName !in database.listCollectionNames().toList()) {
		database.createCollection(collectionName)
		println("Collection '$collectionName' created")
	}
	println("Collection '$collectionName' already exists")
}

suspend fun createFakeData(database: MongoDatabase) {
	val collection = database.getCollection<User>(collectionName)
	val fakeData = List(100) {
		User(
			name = "John Doe $it",
			age = (0..100).random(),
			email = "john.doe${it}@gmail.com",
			createdAt = System.currentTimeMillis() + it * 1000 * 60 * 60 * 24
		)
	}
	collection.insertMany(fakeData)
	println("Fake data created, total: ${fakeData.size} documents")
}

suspend fun deleteFirstUserWithMoreThan70Years(database: MongoDatabase) {
	val collection = database.getCollection<User>(collectionName)
	val user = collection.findOneAndDelete(Filters.gt("age", 70))
	when {
		user != null -> println("User '${user.name}' deleted")
		else -> println("No user found with more than 70 years")
	}
}

suspend fun searchUsersWithMoreThan30Years(database: MongoDatabase) {
	val collection = database.getCollection<User>(collectionName)
	val users = collection.find(Filters.gt("age", 30)).toList()
	println("Users with more than 30 years: ${users.size}")
	users.forEach(::println)
}

suspend fun updateAllUsersAdd5Years(database: MongoDatabase) {
	val collection = database.getCollection<User>(collectionName)
	collection.updateMany(Filters.empty(), Updates.inc("age", 5))
	println("All users updated adding 5 years")
}
