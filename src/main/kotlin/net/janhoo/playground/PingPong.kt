package net.janhoo.playground

import kotlinx.coroutines.experimental.cancelChildren
import kotlinx.coroutines.experimental.channels.Channel
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking

data class Ball(var hits: Int)

fun main(args: Array<String>) = runBlocking<Unit> {
  val table = Channel<Ball>() // a shared table
  launch(coroutineContext) { player("ping", table) }
  launch(coroutineContext) { player("pong", table) }

  table.send(Ball(0)) // serve the ball

  delay(2000) // delay 1 second

  coroutineContext.cancelChildren() // game over, cancel them
}

suspend fun player(name: String, table: Channel<Ball>) {
  for (ball in table) { // receive the ball in a loop
    ball.hits++
    println("$name $ball")
    delay(300) // wait a bit
    table.send(ball) // send the ball back
  }
}
