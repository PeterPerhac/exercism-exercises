object OcrNumbers {

  val numbers: Map[String, String] = Map(
    " _ | ||_|   " -> "0",
    "     |  |   " -> "1",
    " _  _||_    " -> "2",
    " _  _| _|   " -> "3",
    "   |_|  |   " -> "4",
    " _ |_  _|   " -> "5",
    " _ |_ |_|   " -> "6",
    " _   |  |   " -> "7",
    " _ |_||_|   " -> "8",
    " _ |_| _|   " -> "9"
  ).withDefaultValue("?")

  def convert(lines: List[String]): String =
    (lines.head.length, lines.length) match {
      case (w, h) if w % 3 != 0 || h % 4 != 0 => "?"
      case (w, h) => digitLines(w)(lines).mkString(",")
    }

  val digitAt: String => Int => Int => String =
    in => lineWidth => digitOffset =>
      (for {
        y <- (0 until 4)
        x <- (0 until 3)
        charIndex = lineWidth*y + 3*digitOffset + x
      } yield in.charAt(charIndex)).mkString

  val digitLines: Int => List[String] => List[String] =
    w => lines =>
      lines.grouped(4).map{ ls =>
      ((0 until (w / 3)).map(digitAt(ls.mkString)(w) andThen numbers)).mkString
      }.toList

}
