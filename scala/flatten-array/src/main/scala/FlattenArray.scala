object FlattenArray {
  def flatten(in: List[Any]): List[Int] = in match {
    case (h: Int) :: t       => h :: flatten(t)
    case (h: List[Any]) :: t => flatten(h) ++ flatten(t)
    case null :: t           => flatten(t)
    case Nil                 => Nil
  }
}
