package playground

object AnotherExercise extends App {

  def incrementer(a:Double, b:Double):Double = {
     a + b
  }


  println("My total number is "+incrementer(3.4,4.6)+ " the indented incrementer function works")
//  Note use dotty for using the new features of scala 3
  

}
