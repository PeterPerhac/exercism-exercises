import scala.Function.tupled
import scala.language.implicitConversions

object PythagoreanTriplet {

  implicit def threeTupleToTriplet(t: (Int, Int, Int)): Triplet =
    tupled(Triplet.apply _)(t)

  type TripletTuple = (Int, Int, Int)

  final case class Triplet(aa: Int, bb: Int, cc: Int) {
    val (a, b, c) = Seq(aa, bb, cc).sorted match {
      case one +: two +: three +: Seq() => (one, two, three)
      case _ => sys.error("List aa bb cc had fewer or more elements!")
    }

    val isPythagorean: Boolean =
      (a < b && b < c) && (a * a + b * b == c * c)

    def asTuple: TripletTuple = (a, b, c)
  }

  def isPythagorean(triplet: Triplet): Boolean =
    triplet.isPythagorean

  def pythagoreanTriplets(lo: Int, hi: Int): Seq[TripletTuple] = {
    Seq.empty
  }

}
