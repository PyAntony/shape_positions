package shapes

import shapes.Rectangle.Dimensions


object Entry extends App {

  val r1 = Rectangle(Point(10, 12), Dimensions(12, 15))

  /*
  Positions: top-left, top-right, bottom-left, bottom-right
   */
  val r2 = Rectangle(Point(0, 4), Point(10, 4), Point(0, 0), Point(10, 0))
  val r3 = Rectangle((0, 4), (10, 4), (0, 0), (10, 0))

  val rectangle1 = Rectangle(Point(4, 5), Dimensions(4, 8))
  val rectangle2 = Rectangle(Point(0, 1), Dimensions(10, 12))

  RectangleAnalyzer.containment(rectangle1, rectangle2)
  RectangleAnalyzer.intersection(rectangle1, rectangle2)
  RectangleAnalyzer.adjacency(rectangle1, rectangle2)
  RectangleAnalyzer.separation(rectangle1, rectangle2)

  println(rectangle1.isContainedIn(rectangle2))
  println(rectangle1.intersects(rectangle2))
  println(rectangle1.isAdjacentTo(rectangle2))
  println(rectangle1.isSeparatedFrom(rectangle2))

  val circle = Circle(Point(0, 0), 10)

}
