plugins {
    java
	application
	id("com.github.johnrengelman.shadow") version "8.1.1"
	id("org.danilopianini.gradle-java-qa") version "1.32.0"
}

application {
	mainClass.set("unibo.exiled.view.GameView")
}

repositories {
    mavenCentral()
}

dependencies {
	compileOnly("com.github.spotbugs:spotbugs-annotations:4.8.3")

    // Maven dependencies are composed by a group name, a name and a version, separated by colons
    implementation("com.omertron:API-OMDB:1.5")
    implementation("org.jooq:jool:0.9.15")

    /*
     * Simple Logging Facade for Java (SLF4J) with Apache Log4j
     * See: http://www.slf4j.org/
     */
    val slf4jVersion = "2.0.10"
    implementation("org.slf4j:slf4j-api:$slf4jVersion")
    // Logback backend for SLF4J
    runtimeOnly("ch.qos.logback:logback-classic:1.4.14")
    val junitVersion = "5.10.1"
    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitVersion")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
		events(*(org.gradle.api.tasks.testing.logging.TestLogEvent.values()))
		showStandardStreams = true
	}
}