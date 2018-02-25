package net.janhoo.playground

import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

fun main(args: Array<String>) = runBlocking {
  val job = launch { printWithDelay("World!")}
  println("Hello,") // main thread continues here immediately
  job.join()
}

suspend fun printWithDelay(word:String) {
  // launch new coroutine in background and continue
  delay(1000L)
  println("World!")
}
