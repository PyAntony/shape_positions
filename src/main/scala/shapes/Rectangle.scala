package shapes

import shapes.RectangleAnalyzer._

/**
 * A Rectangle in a Cartesian plane.
 *
 * @param tl top left point.
 * @param tr top right point.
 * @param bl bottom left point.
 * @param br bottom right point.
 */
case class Rectangle(tl: Point, tr: Point, bl: Point, br: Point)
  extends Quadrilateral {

  def width: Float = Math.abs(tl.y - bl.y)

  def length: Float = Math.abs(bl.x - br.x)

  def area: Float = width * length

  /**
   * @param that the other Rectangle to compare to.
   * @return leftmost Rectangle comparing the bottom left corner.
   */
  def getLeftMost(that: Rectangle): (Rectangle, Rectangle) = {
    if (bl.x < that.bl.x) (this, that)
    else (that, this)
  }

  /**
   * @param lower the lower point in the range.
   * @param upper the upper point in a range.
   * @param xAxis the axis of the range. Default: x-axis. Set to false to get y-axis instead.
   * @return True if both points in one side are contained between the
   *         lower and upper points (the range).
   */
  def isContained(lower: Point, upper: Point, xAxis: Boolean = true): Boolean = {
    val points = if (xAxis) List(bl, br) else List(bl, tl)
    points.forall(_.isBetween(lower, upper, xAxis))
  }

  /*
  Dot notation for main functionality.
   */
  def isContainedIn(other: Rectangle): Boolean = containment(this, other)

  def intersects(other: Rectangle): Boolean = intersection(this, other)

  def isAdjacentTo(other: Rectangle): Boolean = adjacency(this, other)

  def isSeparatedFrom(other: Rectangle): Boolean = separation(this, other)
}

/**
 * Rectangle companion object.
 */
object Rectangle {
  /*
  Constructors
   */
  def apply(tl: Point, tr: Point, bl: Point, br: Point): Rectangle =
    validateRectangle(new Rectangle(tl, tr, bl, br))

  def apply(tl: (Int, Int), tr: (Int, Int), bl: (Int, Int), br: (Int, Int)): Rectangle =
    validateRectangle(new Rectangle(Point(tl), Point(tr), Point(bl), Point(br)))

  def apply(bl: Point, d: Dimensions): Rectangle = {
    val tl = bl.moveY(d.width)
    validateRectangle(
      new Rectangle(tl, tl.moveX(d.length), bl, bl.moveX(d.length))
    )
  }

  /**
   * Validates Rectangle has 90-degrees angles with only horizontal/vertical lines.
   */
  def validateRectangle(r: Rectangle): Rectangle = {
    val condition1 =
      r.tl.isLessThan(r.tr) && r.bl.isLessThan(r.tl, xAxis = false)
    val condition2 =
      r.bl.x == r.tl.x && r.tl.y == r.tr.y && r.tr.x == r.br.x && r.br.y == r.bl.y

    if (!condition1 || !condition2)
      throw new InvalidRectangleShape
    else
      r
  }

  /**
   * Stores Rectangle dimensions.
   */
  case class Dimensions(width: Int, length: Int)

  /**
   * Exception to be thrown for incorrect instantiations.
   */
  case class InvalidRectangleShape() extends Exception

}



