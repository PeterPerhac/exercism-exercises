case class BookWithPrice(price: Int, id: Int)

object BookStore {
  def total(items: List[Int]): Int = items match {
    case Nil => 0
    case _   => bestValueGrouping(items)
  }

  def bestValueGrouping(items: List[Int]): Int =
    items.map(BookWithPrice(800, _)).map(_.price).sum

  /*
One copy of any of the five books costs $8.

If, however, you buy two different books, you get a 5% discount on those two books.
If you buy 3 different books, you get a 10% discount.
If you buy 4 different books, you get a 20% discount.
If you buy all 5, you get a 25% discount.

Note: that if you buy four books, of which 3 are different titles, you get a 10% discount on the 3 that form part of a set, but the fourth book still costs $8.

 */

}
