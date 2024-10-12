plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	id("org.springframework.boot") version "3.3.4"
	id("io.spring.dependency-management") version "1.1.6"
}

group = "com.colornote"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

repositories {
	mavenCentral()
	maven { url = uri("https://oss.sonatype.org/content/repositories/releases/") }
}

dependencies {
	// Spring Boot dependencies
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	implementation("org.springframework.boot:spring-boot-starter-web")

	// Test dependencies
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	// JDOM for XML Parsing (MusicXML)
	implementation("org.jdom:jdom2:2.0.6")

	// OpenCV for image processing (coloring notes on the sheet music)
	// JavaCV 사용
	implementation("org.bytedeco:javacv:1.5.8")  // JavaCV 의존성

	// 필요한 OpenCV 바이너리 추가 (JavaCV에서 OpenCV 포함)
	// implementation("org.bytedeco:opencv:4.5.3-1.5.8")

	// Apache Commons IO for file handling utilities
	implementation("commons-io:commons-io:2.11.0")

	// If you want to use Jackson for any potential JSON processing
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	// Audiveris (You will need to either manually integrate the JAR or refer to a Maven repo if available)
	implementation(files("libs/audiveris.jar"))
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
