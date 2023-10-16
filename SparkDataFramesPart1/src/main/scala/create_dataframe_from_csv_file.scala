import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

object create_dataframe_from_csv_file {

  def main(args: Array[String]): Unit = {
    println("Apache Spark Application Started ...")

    val spark = SparkSession.builder()
      .appName("Create DataFrame form CSV File")
      .master("local[*]")
      .getOrCreate()

    spark.sparkContext.setLogLevel("ERROR")

    val file_name = "users_csv.csv";
//    val users_df = spark.read.csv(file_name)
//    val users_df = spark.read.option("sep", ",").option("inferSchema", true).option("header", true).csv(file_name)

    val schema = StructType(
      Array(
        StructField("user_id", IntegerType, false),
        StructField("user_name", StringType, false),
        StructField("user_city", StringType, false),
      )
    )
    val users_df = spark.read
      .option("sep", ",")
      .option("header", true)
      .schema(schema)
      .csv(file_name)

    users_df.show()
    users_df.printSchema()

    spark.stop()
    println("Apache Spark Application finished ...")
  }
}
