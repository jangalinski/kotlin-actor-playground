package net.janhoo.playground

import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*


fun main(args: Array<String>) {
  val server = embeddedServer(Netty, 8080) {
    routing {
      get("/") {
        call.respondText("Hello, worl1d!", ContentType.Text.Html)
      }
    }
  }
  server.start(wait = true)
}

fun main3(args: Array<String>) = runBlocking<Unit> {
  val job = launch {
    repeat(1000) { i ->
      println("I'm sleeping $i ...")
      delay(500L)
    }
  }
  delay(1300L) // delay a bit
  println("main: I'm tired of waiting!")
  job.cancel() // cancels the job
  job.join() // waits for job's completion
  println("main: Now I can quit.")
}

fun main2(args: Array<String>) = runBlocking<Unit> {
  val jobs = List(100_000) { // launch a lot of coroutines and list their jobs
    launch {
      delay(1000L)
      print(".")
    }
  }
  jobs.forEach { it.join() } // wait for all jobs to complete
}

fun main1(args: Array<String>) = runBlocking {
  val job = launch { printWithDelay("World!")}
  println("Hello,") // main thread continues here immediately
  job.join()
}

suspend fun printWithDelay(word:String) {
  // launch new coroutine in background and continue
  delay(1000L)
  println("World!")
}
