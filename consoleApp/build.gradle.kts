/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    id("buildlogic.java-application-conventions")
}

dependencies {
    implementation("org.apache.commons:commons-text")
    implementation(project(":shorter"))

    testImplementation(libs.junit.jupiter)

    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

application {
    mainClass = "com.castlelecs.consoleApp.App"
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = application.mainClass
    }
}

tasks.register("printJar") {
    group = "Application"
    description = "Creates JAR and prints the command to run it"

    dependsOn(tasks.jar)

    doLast {
        val jarFile = tasks.getByName("jar").outputs.files.singleFile

        println("java -jar ${jarFile.path}")
    }
}

