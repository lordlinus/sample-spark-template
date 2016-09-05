import java.io.File

import com.holdenkarau.spark.testing.DataFrameSuiteBase
import org.scalatest.prop.Checkers
import org.scalatest._
import org.apache.commons.io.FileUtils._

abstract class BaseSpec extends FunSuite with Matchers with BeforeAndAfterAll with Checkers

class SparkBaseSpec extends BaseSpec with SharedConfig with DataFrameSuiteBase

trait HiveCleanup extends BeforeAndAfterEach with BeforeAndAfterAll {
  this: Suite =>
  override def beforeAll(): Unit = {
    clean()
    super.beforeAll()

  }
  override def beforeEach(): Unit = {
    super.beforeEach()
    clean()
  }
  private def clean(): Unit = {
    Seq("metastore_db", "derby.log")
      .map(new File(_))
      .filter(_.exists())
      .foreach(forceDelete)

  }
}
