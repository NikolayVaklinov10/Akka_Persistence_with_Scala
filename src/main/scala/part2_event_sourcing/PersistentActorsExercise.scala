package part2_event_sourcing

import akka.actor.ActorLogging
import akka.persistence.PersistentActor

import scala.collection.mutable

object PersistentActorsExercise extends App {

  /*

   */
  case class Vote(citizenPID: String, candidate: String)

  class VotingStation extends PersistentActor with ActorLogging {

    // ignore the mutable state for now
    val citizens: mutable.Set[String] = new mutable.HashSet[String]()
    val poll: mutable.Map[String, Int] = new mutable.HashMap[String, Int]()

    override def persistenceId: String = "simple-voting-station"

    override def receiveCommand: Receive = {
      case vote @ Vote(citizenPID, candidate) =>
        if (!citizens.contains(vote.citizenPID)) {
          /*
        follow the pattern

        1) create the event
        2) persist the event
        3) handle a state change after persisting is successful
         */
          persist(vote) { _ => // COMMAND sourcing
            log.info(s"Persisted: $vote")
            handleInternalStateChange(citizenPID, candidate)
          }
        }
      case "print" =>
        log.info("Current state: ")
    }

    def handleInternalStateChange(citizenPID: String, candidate: String): Unit = {

        citizens.add(citizenPID)
        val votes = poll.getOrElse(candidate, 0)
        poll.put(candidate, votes + 1)
      }

    override def receiveRecover: Receive = ???

  }
}
