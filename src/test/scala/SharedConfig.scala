import com.holdenkarau.spark.testing.{LocalSparkContext, SharedSparkContext}
import org.apache.spark.SparkConf
import org.scalatest.{BeforeAndAfterAll, Suite}

/**
  * Created by sunilsattiraju on 2/09/2016.
  */
trait SharedConfig extends BeforeAndAfterAll with SharedSparkContext with HiveCleanup {
  self: Suite =>

  override val conf = new SparkConf()
    .setMaster("local[*]")
    .setAppName("testApp")
    .set("spark.ui.enabled", "false")
    .set("spark.app.id", appID)
    .set("spark.driver.memory", "2G")
    .set("spark.executor.memory", "2G")
    .set("spark.scheduler.minRegisteredResourcesRatio", "1")

  override def beforeAll(): Unit = {
    super.beforeAll()
//    System.setProperty("spark.testing", "true")
  }

  override def afterAll(): Unit = {
    try {
      LocalSparkContext.stop(sc)
    } finally {
      super.afterAll()
    }
  }
}