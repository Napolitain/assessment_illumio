plugins {
    id("java")
}

group = "boucher.maxime"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.testng:testng:7.4.0")
    testImplementation("org.mockito:mockito-core:5.4.0") // or the latest version
}

tasks.test {
    useTestNG()
}

tasks.jar {
    manifest {
        attributes(
            "Main-Class" to "boucher.maxime.Main"  // Correct syntax for setting Main-Class in Kotlin DSL
        )
    }
}
