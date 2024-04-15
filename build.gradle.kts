plugins {
	kotlin("jvm") version "1.9.22"
	application
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.mongodb:mongodb-driver-kotlin-coroutine:5.0.1")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
}

application {
	mainClass = "MainKt"
}
