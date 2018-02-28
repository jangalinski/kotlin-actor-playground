package net.janhoo.playground


fun main(args: Array<String>) {
  val f: (i:Int) -> Boolean = { it % 2 == 0 }
  println(filter(l = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9), f = f))

  println(filter(l= emptyList(), f=f))
}

fun <T> List<T>.tail() = drop(1)
fun <T> List<T>.head() = first()

tailrec fun <T> filter(l: List<T>, res: List<T> = emptyList(), f: (T) -> Boolean): List<T> {
  if (l.isEmpty()) {
    return res
  } else {
    return filter(
      l.tail(),
      if (f(l.head())) {
        res + listOf(l.head())
      } else {
        res
      },
      f)
  }
}
