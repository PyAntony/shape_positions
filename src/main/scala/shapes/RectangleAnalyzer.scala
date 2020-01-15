package shapes


/**
 * Position Analyzer interpreter for Rectangle class.
 */
object RectangleAnalyzer extends PositionAnalyzer[Rectangle] {

  /**
   * Test if a is contained within b.
   */
  override def containment(a: Rectangle, b: Rectangle): Boolean = {
    val (smaller, bigger) = if (a.area < b.area) (a, b) else (b, a)

    a.area == smaller.area &&
      smaller.isContained(bigger.bl, bigger.br) &&
      smaller.isContained(bigger.bl, bigger.tl, xAxis = false)
  }

  /**
   * Test if a intersects b.
   */
  override def intersection(a: Rectangle, b: Rectangle): Boolean = {
    val (left, right) = a.getLeftMost(b)

    !containment(a, b) &&
      right.bl.isBottomLeftFrom(left.tr) &&
      right.tl.isTopLeftFrom(left.br)
  }

  /**
   * Test if a is adjacent to b.
   */
  override def adjacency(a: Rectangle, b: Rectangle): Boolean = {
    !containment(a, b) && !intersection(a, b) && !separation(a, b)
  }

  /**
   * Test if a is separated from b.
   */
  override def separation(a: Rectangle, b: Rectangle): Boolean = {
    val separated = Seq(a.bl, a.br).forall(!_.isBetween(b.bl, b.br)) ||
      Seq(a.bl, a.tl).forall(!_.isBetween(b.bl, b.tl, xAxis = false))

    !containment(a, b) && !intersection(a, b) && separated
  }


}


