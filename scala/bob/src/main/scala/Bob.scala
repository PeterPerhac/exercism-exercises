object Bob {
  def response(statement: String): String ={
    val s = statement.trim
    val acronyms = Set("OK", "DMV")
    val isShouting = s.split("[^a-zA-Z]+").filter{ word =>
      word.toUpperCase == word && word.length > 1 && !acronyms.contains(word.toUpperCase)
    }.nonEmpty
    val isQuestion = s.endsWith("?")
    (isQuestion, isShouting) match {
      case (_, _) if s.isEmpty => "Fine. Be that way!"
      case (true, true) => "Calm down, I know what I'm doing!"
      case (true, false) => "Sure."
      case (false, true) => "Whoa, chill out!"
      case _ => "Whatever."
    }
  }
}
