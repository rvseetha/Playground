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
    useTestNG ()
    systemProperty("jgiven.report.dir", "${layout.buildDirectory.asFile.get()}/jgiven-reports")
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

