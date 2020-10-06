val jar: Jar by tasks
val bootJar: org.springframework.boot.gradle.tasks.bundling.BootJar by tasks

bootJar.enabled = true
bootJar.archiveFileName.set("app.jar")
jar.enabled = true

dependencies {
  implementation("org.springframework.boot:spring-boot-starter-validation")
}
