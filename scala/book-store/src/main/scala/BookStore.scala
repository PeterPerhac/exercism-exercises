case class BookWithPrice(id: Int, price: Int) {
  lazy val withoutDiscount: BookWithPrice = BookWithPrice(id, BookWithPrice.defaultBookPrice)
}
object BookWithPrice {
  val defaultBookPrice: Int = 800
  val create: Int => BookWithPrice = BookWithPrice(_, defaultBookPrice)
  val discounted: Int => (Int => Int) => BookWithPrice =
    id => discount => BookWithPrice(id, discount(defaultBookPrice))
}

object BookStore {

  def total(items: List[Int]): Int =
    groupPrice(items)

  val distinctBooksInGroup: List[Int] => Int =
    _.distinct.size

  val discountFactor: Int => Double = {
    case 1 => 1.00
    case 2 => 0.95
    case 3 => 0.90
    case 4 => 0.80
    case _ => 0.75
  }

  val roundMoney: Double => Int =
    d => (math rint d).toInt

  val discount: Int => Int => Int =
    distinctBooks => ((_: Int) * discountFactor(distinctBooks)) andThen roundMoney

  val priceBookGroup: List[Int] => List[BookWithPrice] =
    group =>
      group.foldLeft(List.empty[BookWithPrice]) { (pricedBooks, bookId) =>
        val alreadyPriced = pricedBooks.find(_.id == bookId)
        val pricedBook =
          alreadyPriced.fold(BookWithPrice.discounted(bookId)(discount(distinctBooksInGroup(group))))(_.withoutDiscount)
        pricedBook :: pricedBooks
    }

  val groupPrice: List[Int] => Int =
    items => priceBookGroup(items).map(_.price).sum

}
