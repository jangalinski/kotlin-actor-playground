import org.jetbrains.kotlin.gradle.dsl.Coroutines

plugins {
  application
  kotlin("jvm") version "1.2.21"
}

kotlin { // configure<org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension>
  experimental.coroutines = Coroutines.ENABLE
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
  compile("org.jetbrains.kotlinx:kotlinx-coroutines-core:0.22.3")
  compile("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:0.22.3")
}
