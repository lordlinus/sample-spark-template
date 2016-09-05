import org.apache.spark.sql.SparkSession

class HelloWorldSpec extends SparkBaseSpec {


  test("simple dataset count test") {

    val sqlCtx = sqlContext
    import sqlCtx.implicits._
    val testDataSet = sqlCtx.sparkSession.createDataset(Seq(
                                                             ("This","is","some","data"),
                                                             ("This","can","be","any Data")
                                         ))

    testDataSet.count shouldBe 2


  }

  test("create DF from Hive sql statement"){
    val spark = SparkSession.builder().enableHiveSupport().getOrCreate()
    import spark.sql
    val sqlDF = sql("select 1")
    println(s"""
         |count:${sqlDF.count}
         |schema: ${sqlDF.printSchema}
       """.stripMargin)
  }
}
