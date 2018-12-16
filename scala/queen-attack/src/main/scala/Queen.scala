case class Queen(x: Int, y: Int)
object Queen{
  def create(x: Int, y: Int):Option[Queen] = Some(Queen(x,y)).filter{ _ =>
    x >= 0 && x < 8 && y >= 0 && y < 8
  }
}
