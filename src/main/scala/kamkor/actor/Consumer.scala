package kamkor.actor

import akka.actor.{ Actor, Props, UnboundedStash, ActorLogging }
import scala.concurrent.duration.DurationInt

case object EndProcessing

class Consumer(val processingTimeMillis: Int) extends Actor with UnboundedStash with ActorLogging {

  import context.dispatcher

  def receive: Receive = {
    case data: Array[Int] => {
      context.become(processing, discardOld = false)
      context.system.scheduler.scheduleOnce(processingTimeMillis.millis, self, EndProcessing)
    }
  }

  def processing: Receive = {
    case data: Array[Int] => stash()
    case EndProcessing => {
      log.debug("endProcessing") // for unit test
      unstashAll()
      context.unbecome()
    }
  }

}

object Consumer {

  def props(processingTimeMillis: Int): Props = Props(new Consumer(processingTimeMillis))

}
