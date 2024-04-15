enum class Commands {
	CREATE,
	DELETE,
	SEARCH,
	UPDATE
}

suspend fun main(args: Array<String>) {
	connect()

	val userInput = args.getOrNull(0).orEmpty().lowercase()
	when (Commands.entries.find { it.name.lowercase() == userInput }) {
		Commands.CREATE -> {
			createCollection(database)
			createFakeData(database)
		}

		Commands.DELETE -> deleteFirstUserWithMoreThan70Years(database)
		Commands.SEARCH -> searchUsersWithMoreThan30Years(database)
		Commands.UPDATE -> updateAllUsersAdd5Years(database)
		else -> println("Invalid command, please use one of:  ${Commands.entries.joinToString(", ") { it.name.lowercase() }}")
	}
}
