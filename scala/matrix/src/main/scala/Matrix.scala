import scala.util.Try

final case class Matrix(matrixDefinition: String){

  val rows: Vector[Vector[Int]] =
    matrixDefinition.split("""\n+""").toVector.map{ line =>
      line.split("""\s+""").toVector.flatMap(s => Try(s.toInt).toOption)
    }
  val columns: Vector[Vector[Int]] = rows.transpose.toVector

  def column(idx: Int): Vector[Int] = columns(idx)
  def row(idx: Int): Vector[Int] = rows(idx)

}
