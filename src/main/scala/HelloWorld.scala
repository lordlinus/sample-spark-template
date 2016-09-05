import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}


object HelloWorld {
  def main(args: Array[String]): Unit = {

    val config = AppConfig
    val sparkConf = new SparkConf().setAll(config.Spark.sparkEnv)
    val sc = new SparkContext(sparkConf)
    val spark = SparkSession.builder().enableHiveSupport().getOrCreate()
    import spark.sql
    val sqlDF = sql("select 1")
    println(sqlDF.count())


  }
}
