plugins {
    java
	application
	id("com.github.johnrengelman.shadow") version "8.1.1"
}

application {
	mainClass.set("unibo.exiled.view.Exiled")
}

repositories {
    mavenCentral()
}

dependencies {
    val junitVersion = "5.10.1"
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
		events(*(org.gradle.api.tasks.testing.logging.TestLogEvent.values()))
	}
    testLogging.showStandardStreams = true
}