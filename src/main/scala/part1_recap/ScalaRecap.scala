package part1_recap

import scala.concurrent.Future
import scala.util.{Failure, Success}

object ScalaRecap extends App {
  val aCondition: Boolean = false
  def myFunction(x: Int): Int = {
    // code
    if (x > 4) 42 else 65
  }
  // instructions vs expressions
  // types + type inference

  // OO features of Scala
  class Animal
  trait Carnivore {
    def eat(a: Animal): Unit
  }

  object Carnivore

  // generics
  abstract class MyList[+A]

  // method notations
  1 + 2 // infix notation
  1.+(2)

  // FP
  val anIncrementer: Int => Int = (x: Int) => x + 1
  anIncrementer(1)

  List(1,2,3).map(anIncrementer)
  // HOF: flatMap, filter
  // for-comparisons

  // Monads: Option, Try

  // Pattern matching!
  val unknown: Any = 2
  val order = unknown match {
    case 1 => "first"
    case 2 => "second"
    case _ => "unknown"
  }

  try {
    // code that can throw an exception
    throw new RuntimeException
  } catch {
    case e: Exception => println("I caught one!")
  }

  /**
   * Scala advanced
   */

  // multithreading

  import scala.concurrent.ExecutionContext.Implicits.global
  val future = Future {
    // long computation here
    // executed on Some other thread
    42
  }
  // map, flatMap, filter + other niceties e.g. recover/recoverWith

  future.onComplete {
    case Success(value) => println(s"I found the meaning of life: $value")
    case Failure(exception) => println(s"I found $exception while searching for the meaning of life!")
  } // on SOME thread

  val partialFunction: PartialFunction[Int, Int] = {
    case 1 => 42
    case 2 => 65
    case _ => 999
  }
  // based on pattern matching!

  // type aliases
  type AkkaReceive = PartialFunction[Any, Unit]
  def receive: AkkaReceive = {
    case 1 => println("hello!")
    case _ => println("confused...")
  }

  // Implicits!
  implicit val timeout = 3000
  def setTimeout(f: () => Unit)(implicit timeout: Int) = f()



}
