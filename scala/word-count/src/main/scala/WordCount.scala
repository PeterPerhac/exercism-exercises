final case class WordCount (str: String) {
  def countWords : Map[String, Int] = str.replaceAll("(\\s'|'\\s|[^'\\w])", " ").split("\\s+").collect{
   case s if s.nonEmpty => s.toLowerCase
  }.groupBy(identity).mapValues(_.size)
}

