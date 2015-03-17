package kamkor

import com.typesafe.config.ConfigFactory

import akka.actor.ActorSystem
import kamkor.actor.Consumer

object ConsumerApp extends App {

  val clusterRole = "consumer"

  val config =
    ConfigFactory.parseString( s"""akka.cluster.roles = [ "$clusterRole" ]""")
      .withFallback(ConfigFactory.load())

  val system = ActorSystem("ClusterSystem", config)

  val processingTimeMillis = config.getInt("consumer.processing-time-millis")
  system.actorOf(Consumer.props(processingTimeMillis), name = clusterRole)

}
