package kamkor.actor

import scala.concurrent.duration.DurationInt

import akka.actor.{ Actor, Props }
import akka.routing.FromConfig

case object Send

class Producer(val sendIntervalMillis: Int, val dataArraySize: Int) extends Actor {

  import context.dispatcher
  context.system.scheduler.schedule(1.second, sendIntervalMillis.millis, self, Send)

  val consumerRouter = context.actorOf(FromConfig.props(), name = "consumerRouter")
  val data = Array.range(0, dataArraySize)

  def receive: Receive = {
    case Send => consumerRouter ! data
  }

}

object Producer {

  def props(sendIntervalMillis: Int, dataArraySize: Int): Props =
    Props(new Producer(sendIntervalMillis, dataArraySize))

}
