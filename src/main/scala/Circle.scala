
import CircleAnalyzer._

/**
 * A circle in a Cartesian plane.
 *
 * @param center center point.
 * @param radius
 */
case class Circle(center: Point, radius: Int) extends Curved {

  /*
  Dot notation for main functionality.
   */
  def isContainedIn(other: Circle): Boolean = containment(this, other)

  def intersects(other: Circle): Boolean = intersection(this, other)

  def isAdjacentTo(other: Circle): Boolean = adjacency(this, other)

  def isSeparatedFrom(other: Circle): Boolean = separation(this, other)

}

/**
 * Circle companion object.
 */
object Circle {
  def apply(center: Point, radius: Int): Circle =
    validateCircle(new Circle(center, radius))

  def validateCircle(c: Circle): Circle = {
    if (c.radius <= 0)
      throw new InvalidCircleShape
    else
      c
  }

  case class InvalidCircleShape() extends Exception

}
