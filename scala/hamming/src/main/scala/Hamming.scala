object Hamming {

  def distance(s1: String, s2: String): Option[Int] =
    PartialFunction.condOpt((s1, s2)) {
      case (a, b) if a.length == b.length =>
        (0 until a.length).count(n => a(n) != b(n))
    }
}
