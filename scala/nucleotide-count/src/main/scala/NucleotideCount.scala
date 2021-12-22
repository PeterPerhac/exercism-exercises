class DNA(sequence: String) {

  import NucleotideCount._

  val isValid: Boolean = sequence.forall(nucleotides.contains)

  lazy val nucleotideCounts: Either[String, Map[Char, Int]] =
    if (isValid)
      Right(sequence.foldLeft(emptyCountMap)((map, c) => map.updated(c, map(c) + 1)))
    else Left("Invalid DNA sequence")

}

object NucleotideCount {
  val nucleotides: Set[Char] = Set('A', 'C', 'G', 'T')
  val emptyCountMap: Map[Char, Int] = nucleotides.map(c => (c, 0)).toMap
}