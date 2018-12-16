import collection.immutable._
import collection.mutable.{Map => MMap}

class School {
  type DB = Map[Int, Seq[String]]

  private val mm = MMap.empty[Int, Seq[String]]

  def add(name: String, g: Int): Unit = {
    mm += (g -> (mm.getOrElse(g, Seq.empty[String]) :+ name))
    ()
  }

  def db: DB = mm.toMap

  def grade(g: Int): Seq[String] = mm.getOrElse(g, Seq.empty)

  def sorted: DB = SortedMap.empty[Int, Seq[String]] ++ mm.mapValues(_.sorted)

}
