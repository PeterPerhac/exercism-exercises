object QueenAttack {

  def canAttack(q1: Queen, q2: Queen): Boolean = {
    val (Queen(x1,y1), Queen(x2,y2)) = (q1, q2)
    def dx = Math.abs(x1-x2)
    def dy = Math.abs(y1-y2)
    x1==x2 || y1==y2 || dx==dy
  }

}
