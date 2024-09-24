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
