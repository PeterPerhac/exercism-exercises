object Etl {

  type OldMap = Map[Int, Seq[String]]
  type NewMap = Map[String, Int]

  val transform: OldMap => NewMap = old =>
    old.foldLeft[NewMap](Map.empty) {
      case (acc, (n, ss)) => acc ++ ss.map(_.toLowerCase -> n)
  }

}
