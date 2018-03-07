package net.janhoo.playground


fun main(args: Array<String>) {
  val f: (i: Int) -> Boolean = { it % 2 == 0 }
  println(filter(list = listOf(1..100).flatten(), predicate = f))

  println(filter(list = emptyList(), predicate = f))
}

fun <T> List<T>.tail() = subList(1, size)
fun <T> List<T>.head() = first()

tailrec fun <T> filter(list: List<T>, result: List<T> = emptyList(), predicate: (T) -> Boolean): List<T> = when {
  list.isEmpty() -> result // no more elements -> return result
  predicate(list.head()) -> filter(list.tail(), result + list.head(), predicate) // first element matches -> add to result, recurse with tail
  else -> filter(list.tail(), result, predicate) // first element does not match -> recurse with tail
}

