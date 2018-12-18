object Series {
  def slices(len: Int, in: String): List[Seq[Int]] =
    in.map(_.asDigit).sliding(len).toList
}
