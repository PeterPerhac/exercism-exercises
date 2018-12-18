object Luhn {

  val valid: String => Boolean =
    cardNo => Some(cardNo).filter(validFormat).filter(validLuhn).isDefined

  val validLuhn: String => Boolean = s =>
    s.replaceAll("\\D", "")
      .map(_.asDigit)
      .reverse
      .zipWithIndex
      .map {
        case (d, idx) if idx % 2 == 0 => d
        case (d, _) if d > 4  => 2 * d - 9
        case (d, _) if d <= 4 => 2 * d
      }
      .sum % 10 == 0

  val validFormat: String => Boolean =
    s => atLeastNDigits(2)(s) && validRegex(s)

  val validRegex: String => Boolean =
    s => """[\d ]{2,}""".r.pattern.matcher(s).matches

  val atLeastNDigits: Int => String => Boolean =
    n => s => s.filter(Character.isDigit).size >= n

}
