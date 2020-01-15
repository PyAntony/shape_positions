
import TestSuite._


object TestRectangle {

  import Rectangle.Dimensions
  import RectangleAnalyzer._

  def main(args: Array[String]): Unit = {

    println("==> RECTANGLE INSTANTIATION TESTS:")

    assertInstance(1) {
      Rectangle(Point(0, 0), Dimensions(2, 2))
    }
    assertInstance(2) {
      Rectangle(Point(-1, 0), Dimensions(200, 2))
    }
    assertInstance(3, assertVal = false) {
      Rectangle(Point(2, 0), Dimensions(0, 2))
    }
    assertInstance(4, assertVal = false) {
      Rectangle(Point(6, 0), Dimensions(200, 0))
    }
    assertInstance(5, assertVal = false) {
      Rectangle(Point(0, 0), Point(6, 0), Point(200, 0), Point(-100, 0))
    }
    assertInstance(6, assertVal = false) {
      Rectangle(Point(6, 0), Point(0, 0), Point(0, 0), Point(6, 0))
    }
    assertInstance(7) {
      Rectangle(Point(0, 4), Point(10, 4), Point(0, 0), Point(10, 0))
    }
    assertInstance(8) {
      Rectangle(Point(10, 1000), Point(500, 1000), Point(10, 2), Point(500, 2))
    }
    assertInstance(9) {
      Rectangle((10, 1000), (500, 1000), (10, 2), (500, 2))
    }
    assertInstance(10, assertVal = false) {
      Rectangle((-10, 10), (10, 10), (-10, 0), (10, 1))
    }

    println("==> RECTANGLE POSITION TESTS:")

    val a = Rectangle(Point(0, 0), Dimensions(8, 10))
    val c = Rectangle(Point(-20, -20), Dimensions(4, 4))

    // Containment tests
    assertProperty(1)(
      containment, a, Rectangle(Point(0, 0), Dimensions(9, 11))
    )
    assertProperty(2)(
      containment, a, a
    )
    assertProperty(3)(
      containment, c, Rectangle(Point(-20, -20), Dimensions(4, 5))
    )
    assertProperty(4, assertVal = false)(
      containment, a, Rectangle(Point(0, 0), Dimensions(7, 10))
    )
    assertProperty(5, assertVal = false)(
      containment, a, Rectangle(Point(0, 0), Dimensions(1, 1))
    )
    assertProperty(6, assertVal = false)(
      containment, c, Rectangle(Point(-20, -20), Dimensions(2, 20))
    )

    // Intersection tests
    assertProperty(7)(
      intersection, a, Rectangle(Point(9, 0), Dimensions(9, 11))
    )
    assertProperty(8)(
      intersection, a, Rectangle(Point(9, 7), Dimensions(3, 4))
    )
    assertProperty(9)(
      intersection, c, Rectangle(Point(-19, -19), Dimensions(30, 30))
    )
    assertProperty(10, assertVal = false)(
      intersection, a, Rectangle(Point(12, 0), Dimensions(2, 2))
    )
    assertProperty(11, assertVal = false)(
      intersection, a, Rectangle(Point(10, 8), Dimensions(3, 2))
    )
    assertProperty(12, assertVal = false)(
      intersection, c, Rectangle(Point(-16, -16), Dimensions(2, 2))
    )

    // Adjacency tests
    assertProperty(13)(
      adjacency, a, Rectangle(Point(10, 0), Dimensions(25, 11))
    )
    assertProperty(14)(
      adjacency, a, Rectangle(Point(0, 8), Dimensions(2, 3))
    )
    assertProperty(15)(
      adjacency, c, Rectangle(Point(-16, -16), Dimensions(2, 3))
    )
    assertProperty(16, assertVal = false)(
      adjacency, a, a
    )
    assertProperty(17, assertVal = false)(
      adjacency, a, Rectangle(Point(20, 20), Dimensions(3, 2))
    )
    assertProperty(18, assertVal = false)(
      adjacency, c, Rectangle(Point(-17, -17), Dimensions(20, 30))
    )

    println("==> PROPERTY TESTS:")
    /*
    Test true statements (laws) among your functions (algebra properties)
     */
    val functions = Seq(containment _, intersection _, adjacency _, separation _)

    assertOneOnly(1)(
      functions, a, Rectangle(Point(0, 0), Dimensions(9, 11))
    )
    assertOneOnly(2)(
      functions, a, Rectangle(Point(0, 0), Dimensions(1, 1))
    )
    assertOneOnly(3)(
      functions, a, Rectangle(Point(9, 7), Dimensions(3, 4))
    )
    assertOneOnly(4)(
      functions, a, Rectangle(Point(10, 8), Dimensions(3, 2))
    )
    assertOneOnly(5)(
      functions, a, Rectangle(Point(0, 8), Dimensions(2, 3))
    )
    assertOneOnly(6)(
      functions, a, Rectangle(Point(20, 20), Dimensions(3, 2))
    )

  }
}
