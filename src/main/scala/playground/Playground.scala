package playground

import akka.actor.{ActorLogging, ActorSystem, Props}
import akka.persistence.PersistentActor

object Playground extends App {


  /**
   * A simple persistent actor that just logs all commands and events.
   */
  class SimplePersistentActor extends PersistentActor with ActorLogging {
    override def persistenceId: String = "simple-persistence"

    override def receiveCommand: Receive = {
      case message => log.info(s"Received: $message")
    }

    override def receiveRecover: Receive = {
      case event => log.info(s"Recovered: $event")
    }
  }

  val system = ActorSystem("Playground")
  val simpleActor = system.actorOf(Props[SimplePersistentActor], "simplePersistentActor")
  simpleActor ! "I love Akka!"


}
