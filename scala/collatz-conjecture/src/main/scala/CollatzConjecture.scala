object CollatzConjecture {
  def steps(n: Int): Option[Int] = {
    def ss(s: Int)(num: Int): Int = num match {
      case 1 => s
      case x if x % 2 == 0 => ss(s + 1)(x / 2)
      case x => ss(s + 1)(3 * x + 1)
    }
    Some(n).filter(_ > 0).map(ss(0))
  }
}
