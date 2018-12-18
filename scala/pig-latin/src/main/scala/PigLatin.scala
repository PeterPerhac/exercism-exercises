object PigLatin {

  val vowels: Set[Char] = Set('a', 'e', 'i', 'o', 'u')

  def translate(str: String): String =
    str
      .split("\\s+")
      .collect {
        case s if s.nonEmpty => translateWord(s)
      }
      .mkString(" ")

  def translateWord(w: String): String = w.toList match {
    case YAfterConsonantCluster(consonants, rest)        => (rest :+ consonants :+ "ay").mkString
    case s @ h :: 'q' :: 'u' :: t if !vowels.contains(h) => (t :+ h :+ "quay").mkString
    case s @ 't' :: 'h' :: 'r' :: t                      => (t :+ "thray").mkString
    case s @ 's' :: 'c' :: 'h' :: t                      => (t :+ "schay").mkString
    case s @ 'y' :: 't' :: t                             => (s :+ "ay").mkString
    case s @ 't' :: 'h' :: t                             => (t :+ "thay").mkString
    case s @ 'c' :: 'h' :: t                             => (t :+ "chay").mkString
    case s @ 'q' :: 'u' :: t                             => (t :+ "quay").mkString
    case s @ 'x' :: 'r' :: t                             => (s :+ "ay").mkString
    case s @ h :: _ if vowels.contains(h)                => s.mkString + "ay"
    case h :: t if !vowels.contains(h)                   => (t :+ h :+ "ay").mkString
  }

  object YAfterConsonantCluster {
    def unapply(sl: List[Char]): Option[(String, String)] = {
      val (p1, p2) = sl.span(_ != 'y')
      Some(p1.mkString -> p2.mkString).filter {
        case (start, rest) => start.nonEmpty && !start.exists(vowels.contains)
      }
    }
  }
}
