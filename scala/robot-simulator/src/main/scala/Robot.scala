sealed abstract class Bearing(xAdv: Int, yAdv: Int){
  def left: Bearing
  def right: Bearing
  def advance(cs:(Int, Int)): (Int, Int) = { val (x,y) = cs; (x+xAdv, y+yAdv) }
}
object Bearing{
  case object West extends Bearing(-1, 0){ override def left = South; override def right = North }
  case object East extends Bearing(1, 0){ override def left = North; override def right = South }
  case object South extends Bearing(0, -1){ override def left = East; override def right = West}
  case object North extends Bearing(0, 1){ override def left = West; override def right = East }
}

sealed abstract class Instruction(val code: Char)
object Instruction {
  case object Left extends Instruction('L')
  case object Right extends Instruction('R')
  case object Advance extends Instruction('A')

  def resolveInstructionCode(code: Char): Option[Instruction] =
    List(Left, Right, Advance).find(_.code == code)

}

final case class Robot(bearing: Bearing, coordinates: (Int, Int)) {
  import Instruction._

  def advance: Robot = copy(coordinates=bearing.advance(coordinates))
  def turnLeft: Robot = copy(bearing=bearing.left)
  def turnRight: Robot = copy(bearing=bearing.right)

  def simulate(instructions: String): Robot = {
    instructions.flatMap(resolveInstructionCode).foldLeft(this){ (robot, instruction) =>
      instruction match {
        case Left => robot.turnLeft
        case Right => robot.turnRight
        case Advance => robot.advance
      }
    }
  }

}
