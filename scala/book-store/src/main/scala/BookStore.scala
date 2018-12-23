import scala.collection.mutable

object BookStore {

  private val bookPrice = 8
  private val discounts = Map(1 -> 100, 2 -> 95, 3 -> 90, 4 -> 80, 5 -> 75).withDefaultValue(75)
  private val cache = new mutable.HashMap[List[Int], Int]()

  def total(books: List[Int]): Int =
    cache.getOrElseUpdate(books, calculate_total(books))

  private def calculate_total(books: List[Int]): Int =
    books match {
      case Nil => 0 //prevent .min on an empty collection
      case _ => subsets(books.distinct).map(subset => groupPrice(subset.length) + total(books.diff(subset))).min
    }

  private def groupPrice(bookCount: Int): Int =
    bookPrice * bookCount * discounts(bookCount)

  def subsets(selection: List[Int]): List[List[Int]] =
    (1 to selection.size).flatMap(selection.combinations).toList

}