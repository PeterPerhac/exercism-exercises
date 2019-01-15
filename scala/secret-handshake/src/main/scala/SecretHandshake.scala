object SecretHandshake {

  val bitMap: Map[Int, String] = Map (
    ( 0, "wink"),
    ( 1, "double blink"),
    ( 2, "close your eyes"),
    ( 3, "jump")
  )

  def isBitSet(bitIndex: Int, decimal: Int): Boolean =
    (decimal & Math.pow(2, bitIndex).toInt) > 0

  def commands(n: Int): List[String] = {
    val list = List.tabulate(4)(idx => Some(bitMap(idx)).filter(_ => isBitSet(idx, n))).flatten
    if (isBitSet(4, n)) list.reverse else list
  }

}
