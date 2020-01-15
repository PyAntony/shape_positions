package shapes


/**
 * A point in a Cartesian plane.
 *
 * @param x x coordinate.
 * @param y y coordinate.
 */
final case class Point(x: Int, y: Int) {

  def moveX(n: Int): Point = Point(x + n, y)

  def moveY(n: Int): Point = Point(x, y + n)

  /**
   * Tests if point is in range [lower, upper], inclusive. X-axis as default.
   * Set 'xAxis' to False to test in y-axis.
   */
  def isBetween(lower: Point, upper: Point, xAxis: Boolean = true): Boolean = {

    if (xAxis) x >= lower.x && x <= upper.x
    else y >= lower.y && y <= upper.y
  }

  /**
   * Tests if point coordinate is less than the other. X-axis as default.
   */
  def isLessThan(other: Point, xAxis: Boolean = true): Boolean = {
    if (xAxis) x < other.x
    else y < other.y
  }

  /**
   * Tests if point coordinate is greater than the other. X-axis as default.
   */
  def isGreaterThan(other: Point, xAxis: Boolean = true): Boolean = {
    if (xAxis) x > other.x
    else y > other.y
  }

  def isBottomLeftFrom(other: Point): Boolean =
    isLessThan(other) && isLessThan(other, xAxis = false)

  def isTopLeftFrom(other: Point): Boolean =
    isLessThan(other) && isGreaterThan(other, xAxis = false)

}

object Point {
  def apply(x: Int, y: Int): Point = new Point(x, y)

  def apply(t: (Int, Int)): Point = new Point(t._1, t._2)
}
