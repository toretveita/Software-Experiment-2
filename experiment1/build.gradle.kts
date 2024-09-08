plugins {
	id("org.springframework.boot") version "3.0.0"
	id("io.spring.dependency-management") version "1.0.15.RELEASE"
	kotlin("jvm") version "1.8.10" // Ensure Kotlin plugin is included if using Kotlin
}

group = "com.example"
version = "1.0-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	// Spring Boot Starter Web
	implementation("org.springframework.boot:spring-boot-starter-web")

	// Spring Boot Test Starter
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	// RestAssured for API testing
	testImplementation("io.rest-assured:rest-assured:5.0.0")

	// Optional JSON handling
	testImplementation("com.fasterxml.jackson.core:jackson-databind:2.13.4.1")
	testImplementation("com.google.code.gson:gson:2.10.1")
}

tasks.test {
	useJUnitPlatform()
}
