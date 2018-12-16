object AllYourBase {

  val toDecimal: Int => List[Int] => Int = base => digits =>
    digits.reverse.zipWithIndex.foldLeft(0){
      case ( acc, (e, power) ) => acc + (e * Math.pow(base, power)).toInt
    }

  val fromDecimal: Int => Int => List[Int] = base => decimal => {
    import math.Integral.Implicits._
    var n = decimal
    var l = List.empty[Int]
    while ( n > 0 ) {
      val (d, r) = n /% base
      l = r :: l
      n = d
    }
    l match {
      case Nil => List(0)
      case list => list
    }
  }

  val converter: Int => Int => List[Int] => List[Int] =
    baseIn => baseOut => toDecimal(baseIn) andThen fromDecimal(baseOut)

  def rebase(baseIn: Int, digits: List[Int], baseOut: Int): Option[List[Int]] =
    Some(digits.dropWhile(_ == 0)).collect{
      case ds if ds.forall(d => d < baseIn && d >= 0) && baseIn > 1 && baseOut > 1 =>
        converter(baseIn)(baseOut)(ds)
    }

}
