object ArmstrongNumbers {

  val isArmstrongNumber: Int => Boolean =
    n => (getDigits andThen armstrongValue)(n) == n

  val getDigits: Int => List[Int] =
    _.toString.toList.map(_.asDigit)

  val armstrongValue: List[Int] => Int =
    digits => digits.foldLeft(0)((acc, e) => acc + Math.pow(e, digits.length).toInt)

}
