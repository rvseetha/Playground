import java.nio.charset.StandardCharsets

plugins {
    kotlin("jvm") version "2.2.0"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("com.tngtech.jgiven:jgiven-testng:2.0.3")
    testImplementation("com.tngtech.jgiven:jgiven-core:2.0.3")
    testImplementation("com.tngtech.jgiven:jgiven-html5-report:2.0.3")
}

tasks.test {
    useTestNG {
        suites("src/test/resources/testSuites/testSuite.xml")
    }
    systemProperty("jgiven.report.dir", "${layout.buildDirectory.asFile.get()}/jgiven-reports")
    ignoreFailures = true
}

kotlin {
    jvmToolchain(21)
}
tasks.register<JavaExec>("jgivenReport") {
    group = "verification"
    description = "Generates JGiven HTML report"
    classpath = files(
        "build/classes/kotlin/test",
        "build/resources/test",
        configurations.testRuntimeClasspath,
        "build/libs"
    )
    mainClass.set("com.tngtech.jgiven.report.ReportGenerator")
    args = listOf(
        "--format=html5",
        "--sourceDir=build/jgiven-reports",
        "--targetDir=build/reports/jgiven-html"
    )
    dependsOn("test")
}



tasks.register("customizeJGivenReport") {
    dependsOn("jgivenReport")
    val inputFile = layout.buildDirectory.file("reports/jgiven-html/data/metaData.js")
    doLast {
        val metaDataFile = inputFile.get().asFile
        if (metaDataFile.exists()) {
            val originalText = metaDataFile.readText(StandardCharsets.UTF_8)
            val modifiedText = originalText.replace("JGiven Report", "Customised JGiven Report")
            if (originalText != modifiedText) {
                metaDataFile.writeText(modifiedText, StandardCharsets.UTF_8)
                println("Replaced JGiven report title in '$metaDataFile' with 'Customised JGiven Report'")
            } else {
                println("Report title already set to 'Customised JGiven Report' in '$metaDataFile'. No changes needed.")
            }
        } else {
            println("File '$metaDataFile' does not exist. Skipping report customization.")
        }
    }
}

tasks.register("fixJGivenStatus") {
    dependsOn("test")
    doLast {
        val reportsDir = file("build/jgiven-reports")
        if (reportsDir.exists()) {
            reportsDir.walkTopDown()
                .filter { it.isFile && it.extension == "json" }
                .forEach { jsonFile ->
                    val text = jsonFile.readText(StandardCharsets.UTF_8)
                    val newText = text.replace("\"status\": \"Aborted\"", "\"status\": \"Failed\"")
                    println("Trying to update status for file: ${jsonFile.path}")
                    if (text != newText) {
                        jsonFile.writeText(newText, StandardCharsets.UTF_8)
                        println("Updated status in ${jsonFile.path}")
                    }
                }
        }
    }
}

tasks.named("jgivenReport") {
    dependsOn("fixJGivenStatus")
}
