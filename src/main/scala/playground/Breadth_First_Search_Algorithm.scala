package playground

object Breadth_First_Search_Algorithm extends App {

  import scala.collection.immutable.Queue

  def breadth_first_traverse[Node](node: Node, f: Node => Queue[Node]): Stream[Node] = {
    def recurse(q: Queue[Node]): Stream[Node] = {
      if (q.isEmpty) {
        Stream.Empty
      } else {
        val (node, tail) = q.dequeue
        node #:: recurse(tail ++ f(node))
      }
    }

    node #:: recurse(Queue.empty ++ f(node))
  }

}
