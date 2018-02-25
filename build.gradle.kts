import org.jetbrains.kotlin.gradle.dsl.Coroutines
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotlinVersion = plugins.getPlugin(KotlinPluginWrapper::class.java).kotlinPluginVersion
val kotlinCoroutinesVersion = "0.22.3"
val ktorVersion = "0.9.1"

plugins {
  application
  kotlin("jvm") version "1.2.21"
  idea
}

kotlin {
  experimental.coroutines = Coroutines.ENABLE
}

application {
  group = "net.janhoo.playground"
  applicationName = "kotlin-actor-playground"
  version = "1.0-SNAPSHOT"

  mainClassName = "net.janhoo.playground.MainKt"
}

repositories {
  mavenLocal()
  mavenCentral()
  jcenter()

  maven("https://dl.bintray.com/kotlin/kotlinx")
  maven ("https://dl.bintray.com/kotlin/ktor" )
}


dependencies {
  implementation(kotlin("stdlib-jdk8"))

  "org.jetbrains.kotlinx:kotlinx-coroutines".let {
    compile("$it-core:$kotlinCoroutinesVersion")
    compile("$it-jdk8:$kotlinCoroutinesVersion")

  }

  compile("io.ktor:ktor-server-netty:$ktorVersion")
  compile("ch.qos.logback:logback-classic:1.2.1")


  compile("io.arrow-kt:arrow-core:0.6.1")
  compile("io.arrow-kt:arrow-typeclasses:0.6.1")
  compile("io.arrow-kt:arrow-instances:0.6.1")
  compile("io.arrow-kt:arrow-data:0.6.1")
  compile("io.arrow-kt:arrow-syntax:0.6.1")
  kapt("io.arrow-kt:arrow-annotations-processor:0.6.1")
}

tasks {
  withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
  }

  withType<Test> {
    testLogging.showStandardStreams = true
  }

  withType<Jar> {
    manifest {
      attributes["Main-Class"] = application.mainClassName
    }
    from(configurations.runtime.map { if (it.isDirectory) it else zipTree(it) })
  }

  withType<GradleBuild> {
    finalizedBy("publishToMavenLocal")
  }

}
