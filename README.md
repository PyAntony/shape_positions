# Shape Positions

Package to calculate positions of 2 shapes in a Cartesian plane. Shapes supported: rectangles 
and circles. Positions include:
- containment: one shape containes the other.
- intersection: shapes overlap (no containment).
- adjacency: shapes touch at least in one point.
- separation: shapes are entirely separated.

### Technologies

- Scala 2.13.1
- sbt 1.3.6

### Usage

Clone this repo and run "sbt package". It generates the Jar file in the target directory. 
You can include that jar as an external library in your Scala/Java projects (don't forget to 
add the dependency in your classpath).

There are 3 ways to instantiate rectangles:
- with a Point and Dimensions class (easiest way).
- with 4 Points.
- with 4 tuples.  
For the last 2 you must follow the correct order (positions) specified in the comment bellow:
```scala
  val r1 = Rectangle(Point(10, 12), Dimensions(12, 15))

  /*
  Positions: top-left, top-right, bottom-left, bottom-right
   */
  val r2 = Rectangle(Point(0, 4), Point(10, 4), Point(0, 0), Point(10, 0))
  val r3 = Rectangle((0, 4), (10, 4), (0, 0), (10, 0))
```

Valid rectangles have 90-degree angles and only vertical or horizontal sides; no diagonal sides allowed.
To determine the position of the rectangles you can use the RectangleAnalyzer class or 
use the traditional dot notation. Examples:
```scala
  val rectangle1 = Rectangle(Point(4, 5), Dimensions(4, 8))
  val rectangle2 = Rectangle(Point(0, 1), Dimensions(10, 12))

  RectangleAnalyzer.containment(rectangle1, rectangle2)
  RectangleAnalyzer.intersection(rectangle1, rectangle2)
  RectangleAnalyzer.adjacency(rectangle1, rectangle2)
  RectangleAnalyzer.separation(rectangle1, rectangle2)

  rectangle1.isContainedIn(rectangle2)
  rectangle1.intersects(rectangle2)
  rectangle1.isAdjacentTo(rectangle2)
  rectangle1.isSeparatedFrom(rectangle2)
```

Circle positions work in the exact same fashion using the CircleAnalyzer class or using dot notation. 
To create a circle just pass a point and the radius:
```scala
  val circle = Circle(Point(0, 0), 10)
```

### Design

There is trait (interface) that declares our system in the traits.scala file:
```scala
    /**
    * Analyzer for position of 2 shapes.
    */
    trait PositionAnalyzer[T <: Shape] {
    def containment(a: T, b: T): Boolean
    def intersection(a: T, b: T): Boolean
    def adjacency (a: T, b: T): Boolean
    def separation(a: T, b: T): Boolean
    }
```
We can extend this trait to create an interpreter (an specific implementation) for any Shape we want. 
Shapes also have a hierarchy, for example, Rectangle extends Quadrilateral and Quadrilateral extends Shape. 
Project include multiple test cases (for Rectangle and Circle) in the test directory. 



