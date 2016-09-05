import com.typesafe.config.ConfigFactory

import scala.collection.JavaConversions._

object AppConfig {

  val config = ConfigFactory.load("env")

  object Spark {
    val sparkEnv = config.getConfig("spark.env").entrySet().map { entry =>
      (entry.getKey, entry.getValue.render().replace("\"", ""))
    }
  }

}