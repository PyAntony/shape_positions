package shapes


object CircleAnalyzer extends PositionAnalyzer[Circle] {

  override def containment(a: Circle, b: Circle): Boolean =
    Math.sqrt(distanceSqrt(a, b)) + a.radius <= b.radius

  override def intersection(a: Circle, b: Circle): Boolean =
    !containment(a, b) && radSumSqrt(a, b) > distanceSqrt(a, b)

  override def adjacency(a: Circle, b: Circle): Boolean =
    distanceSqrt(a, b) == radSumSqrt(a, b)

  override def separation(a: Circle, b: Circle): Boolean =
    distanceSqrt(a, b) > radSumSqrt(a, b)

  /*
  Positions can be found by finding 2 metrics: distance from center points and sum of radii.
  For example, intersection can be defined as:
    SQRT((x0 - x1)^2 + (y0 - y1)^2) <= (Rad0 + Rad1)
      where x0, x1, y0, y1 represent the coordinates of the points' centers.

   We can also replace the above formula with:
    (x0 - x1)^2 + (y0 - y1)^2 <= (Rad0 + Rad1)^2
    to avoid taking SQRT.
   */

  /**
   * Get circles' distance measured from center points (no SQRT needed).
   */
  private def distanceSqrt(a: Circle, b: Circle): Int =
    sqr(a.center.x - b.center.x) + sqr(a.center.y - b.center.y)

  /**
   * Get sum of radii squared.
   */
  private def radSumSqrt(a: Circle, b: Circle): Int = {
    sqr(a.radius + b.radius)
  }

  private def sqr(x: Int): Int = x * x
}
