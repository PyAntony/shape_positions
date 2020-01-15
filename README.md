# Shape Positions

Library to calculate positions of 2 shapes in a Cartesian plane. Shapes supported: Rectangle 
and Circle. Positions include:
- containment: one shape containes the other.
- intersection: shapes overlap (no containment).
- adjacency: shapes touch at least in one point.
- separation: shapes are entirely separated.

### Technologies

- Scala 2.13.1
- sbt 1.3.6

### Usage
To generate your own jar file clone this repo and run "sbt package" in root directory (assumes you have sbt installed). 
If you want to download the jar file directly [-> click here <-](https://cc6e750869d1bf4c575d93c62ceaffbd880f62fdc70d92005eedad24f5865.s3.amazonaws.com/rectangles2_2.13-0.1.jar)  

You can include that jar as an external library in your Scala/Java projects. Don't forget to 
add the dependency in your classpath (If you are using sbt you can just paste the jar file in the lib directory). 
You can test this library within your Java environment (Scala/Java interoperability); for example:
```java
import shapes.*;

public class Test {
  public static void main(String[] args) {

    var rectangle1 = new Rectangle(
      new Point(0, 8), new Point(10, 8),
      new Point(0, 0), new Point(10, 0)
    );
    var rectangle2 = new Rectangle(
      new Point(0, 12), new Point(20, 12),
      new Point(0, 2), new Point(20, 2)
    );

    var circle1 = new Circle(new Point(-3, 2), 10);
    var circle2 = new Circle(new Point(-8, 3), 6);
    
    rectangle1.intersects(rectangle2)
    circle1.isAdjacentTo(circle2)
  }
}
```

There are 3 ways to instantiate rectangles (Scala syntax):
- using a Point and Dimensions class (easiest way).
- passing 4 Points.
- passing 4 tuples.  
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

There is a trait (interface) that declares the abstract system:
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
Shapes also have a hierarchy:
```scala
/**
 * Shape types
 */
sealed trait Shape
trait Triangle extends Shape
trait Quadrilateral extends Shape
trait Polygon extends Shape
trait Curved extends Shape
```
Having declared our operations and data types (algebraic structure) we can expand the system by adding multiple 
PositionAnalyzer interpreters and Shapes.

Project include multiple test cases (for Rectangle and Circle) in the src/test/scala/shapes directory. 

### To Do

- [ ] Create Shape Generators to automate the test cases. 


