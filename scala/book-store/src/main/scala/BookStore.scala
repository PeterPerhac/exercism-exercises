import BookWithPrice.bookWithDiscount

case class BookWithPrice(id: Int, price: Int) {
  lazy val withoutDiscount: BookWithPrice = BookWithPrice(id, BookWithPrice.defaultBookPrice)
}

object BookWithPrice {
  val defaultBookPrice: Int = 800

  def book(id: Int): BookWithPrice = BookWithPrice(id, defaultBookPrice)

  def bookWithDiscount(id: Int, discountApplicator: Int => Int): BookWithPrice =
    BookWithPrice(id, discountApplicator(defaultBookPrice))
}

object BookStore {

  def total(items: List[Int]): Int =
    items.combinations(math.min(items.size, 5)).map {
      foo =>
        println(foo)
        foo
    }.map(groupPrice).map(i => {
      println(i); i
    }).min

  val discountFactor: Map[Int, Double] = Map(1 -> 1.00, 2 -> 0.95, 3 -> 0.90, 4 -> 0.80).withDefaultValue(0.75)

  val roundMoney: Double => Int =
    d => (math rint d).toInt

  val discount: Int => Int => Int =
    distinctBooks => originalPrice =>
      roundMoney(originalPrice * discountFactor(distinctBooks))

  def priceBookGroup(group: List[Int]): List[BookWithPrice] =
    group.foldLeft(List.empty[BookWithPrice]) { (pricedBooks, bookId) =>
      val alreadyPriced = pricedBooks.find(_.id == bookId)
      val pricedBook = alreadyPriced.fold(bookWithDiscount(bookId, discount(group.distinct.size)))(_.withoutDiscount)
      pricedBook :: pricedBooks
    }

  def groupPrice(items: List[Int]): Int =
    priceBookGroup(items).map(_.price).sum

}
