object NumberType extends Enumeration {
  type NumberType = Value
  val Deficient = Value(-1)
  val Perfect = Value(0)
  val Abundant = Value(1)
}

object PerfectNumbers {
  import NumberType._

  val classify: Int => Either[String, NumberType] =
    n => Either.cond( n > 0,
      NumberType(aliquotSum(n) compare n),
      "Classification is only possible for natural numbers.")

  val aliquotSum : Int => Int =
    n => (1 to n/2).filter( n % _ == 0 ).sum

}
