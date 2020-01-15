
/**
 * Analyzer for position of 2 shapes.
 */
trait PositionAnalyzer[T <: Shape] {
  def containment(a: T, b: T): Boolean
  def intersection(a: T, b: T): Boolean
  def adjacency (a: T, b: T): Boolean
  def separation(a: T, b: T): Boolean
}

/**
 * Shape type
 */
sealed trait Shape
trait Triangle extends Shape
trait Quadrilateral extends Shape
trait Polygon extends Shape
trait Curved extends Shape
