plugins {
  application
  kotlin("jvm") version "1.2.21"
}

application {
  mainClassName = "net.janhoo.playground.MainKt"
}

repositories {
  mavenLocal()
  mavenCentral()
}

dependencies {
  implementation(kotlin("stdlib-jdk8"))
}
