
import TestSuite._


object TestCircle {

  import CircleAnalyzer._

  def main(args: Array[String]): Unit = {

    println("==> CIRCLE INSTANTIATION TESTS:")
    assertInstance(1) {
      Circle(Point(2, 4), 4)
    }
    assertInstance(1) {
      Circle(Point(-100, -59), 700)
    }
    assertInstance(1, assertVal = false) {
      Circle(Point(2, 2), 0)
    }

    println("==> CIRCLE POSITION TESTS:")
    val a = Circle(Point(0, 0), 50)
    val c = Circle(Point(-50, -50), 500)
    val m = Circle(Point(0, 0), 1)

    // Containment tests
    assertProperty(1)(
      containment, Circle(Point(0, 0), 2), a
    )
    assertProperty(2)(
      containment, Circle(Point(12, 14), 2), a
    )
    assertProperty(3, assertVal = false)(
      containment, Circle(Point(25, 25), 50), a
    )

    // Intersection tests
    assertProperty(7)(
      intersection, Circle(Point(10, 10), 50), a
    )
    assertProperty(8)(
      intersection, Circle(Point(49, 0), 5), a
    )
    assertProperty(9, assertVal = false)(
      intersection, Circle(Point(12, 14), 2), a
    )

    // Adjacency tests tests
    assertProperty(7)(
      adjacency, m, Circle(Point(2, 0), 1)
    )
    assertProperty(8)(
      adjacency, m, Circle(Point(10, 0), 9)
    )
    assertProperty(9, assertVal = false)(
      adjacency, a, c
    )

    println("==> PROPERTY TESTS:")

    val functions = Seq(containment _, intersection _, adjacency _, separation _)

    assertOneOnly(1)(
      functions, m, Circle(Point(10, 0), 9)
    )
    assertOneOnly(2)(
      functions, Circle(Point(10, 10), 50), a
    )
    assertOneOnly(3)(
      functions, Circle(Point(12, 14), 2), a
    )
    assertOneOnly(4)(
      functions, c, a
    )

  }
}
