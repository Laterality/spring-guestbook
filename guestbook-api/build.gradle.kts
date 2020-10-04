val jar: Jar by tasks
val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks

bootJar.enabled = true
bootJar.archiveFileName.set("app.jar")
jar.enabled = true

dependencies {
    implementation("dev.miku:r2dbc-mysql:0.8.2.RELEASE")
}
