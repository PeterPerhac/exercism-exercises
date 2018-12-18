import org.scalatest.{FunSuite, Matchers}

/** @version 1.2.0 */
class WordCountTest extends FunSuite with Matchers {

  test("count one word") {
    WordCount("word").countWords should be(Map(("word", 1)))
  }

  test("count one of each word") {
    WordCount("one of each").countWords should be(Map(("one", 1), ("of", 1), ("each", 1)))
  }

  test("multiple occurrences of a word") {
    WordCount("one fish two fish red fish blue fish").countWords should be(
      Map(("blue", 1), ("two", 1), ("fish", 4), ("one", 1), ("red", 1)))
  }

  test("handles cramped lists") {
    WordCount("one,two,three").countWords should be(Map(("one", 1), ("two", 1), ("three", 1)))
  }

  test("handles expanded lists") {
    WordCount("one,\ntwo,\nthree").countWords should be(Map(("one", 1), ("two", 1), ("three", 1)))
  }

  test("ignore punctuation") {
    WordCount("car: carpet as java: javascript!!&@$%^&").countWords should be(
      Map(("as", 1), ("car", 1), ("java", 1), ("carpet", 1), ("javascript", 1)))
  }

  test("include numbers") {
    WordCount("testing, 1, 2 testing").countWords should be(Map(("testing", 2), ("1", 1), ("2", 1)))
  }

  test("normalize case") {
    WordCount("go Go GO Stop stop").countWords should be(Map(("go", 3), ("stop", 2)))
  }

  test("with apostrophes") {
    WordCount("First: don\'t laugh. Then: don\'t cry.").countWords should be(
      Map(("laugh", 1), ("don't", 2), ("then", 1), ("first", 1), ("cry", 1)))
  }

  test("with quotations") {
    WordCount("Joe can\'t tell between \'large\' and large.").countWords should be(
      Map(("can't", 1), ("large", 2), ("joe", 1), ("between", 1), ("tell", 1), ("and", 1)))
  }

  test("multiple spaces not detected as a word") {
    WordCount(" multiple   whitespaces").countWords should be(Map(("multiple", 1), ("whitespaces", 1)))
  }
}
