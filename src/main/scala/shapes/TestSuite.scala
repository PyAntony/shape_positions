package shapes


import scala.util.{Failure, Success, Try}


/**
 * Contains helper methods for testing cases.
 */
object TestSuite {

  /**
   * Asserts an instance was successfully created.
   *
   * @param testNumber to enumerate test cases.
   * @param assertVal  false to assert an Exception.
   * @param instance   expression to run to generate the instance.
   * @tparam A instance type
   */
  def assertInstance[A](testNumber: Int, assertVal: Boolean = true)
                       (instance: => A): Unit = {
    def display(): Unit = println(s"test $testNumber failed.")

    val isInstance = Try(instance) match {
      case Success(_) => true
      case Failure(_) => false
    }

    if (isInstance != assertVal)
      display()
  }

  /**
   * Asserts the property/function returns the expected boolean.
   *
   * @param testNumber to enumerate test cases.
   * @param assertVal  false to assert the property will fail.
   * @param f          function/property to test.
   * @param a          shape.
   * @param b          other shape.
   * @tparam T shape type.
   */
  def assertProperty[T](testNumber: Integer, assertVal: Boolean = true)
                       (f: (T, T) => Boolean, a: T, b: T): Unit = {
    def display(): Unit = println(s"test $testNumber failed.")

    if (f(a, b) != assertVal)
      display()
  }

  /**
   * Asserts only 1 property/function holds among multiple properties.
   *
   * @param testNumber to enumerate test cases.
   * @param functions  List of properties to test.
   * @param a          shape.
   * @param b          other shape.
   * @tparam T shape type.
   */
  def assertOneOnly[T](testNumber: Integer)
                      (functions: Seq[(T, T) => Boolean], a: T, b: T): Unit = {
    def display(): Unit = println(s"test $testNumber failed.")

    if (functions.map(_ (a, b)).count(_ == true) != 1)
      display()
  }

}
