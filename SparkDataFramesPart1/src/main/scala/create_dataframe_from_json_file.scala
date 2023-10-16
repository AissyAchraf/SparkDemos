import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}

object create_dataframe_from_json_file {

  def main(args: Array[String]): Unit = {

    println("Apache Spark Application started ...")

    val spark = SparkSession.builder()
      .appName("Create DataFrame form CSV File")
      .master("local[*]")
      .getOrCreate()

    spark.sparkContext.setLogLevel("ERROR")

    val json_file = "users_json.json"
    val users_df = spark.read.json(json_file)

    users_df.show()
    users_df.printSchema()

    val schema = StructType(Array(
      StructField("user_id", IntegerType, true),
      StructField("user_name", StringType, true),
      StructField("user_city", StringType, true),
    ))

    val json_file_multiLine = "users_json_multiline.json"
    val users_df2 = spark.read.option("multiLine", true).schema(schema).json(json_file_multiLine)

    users_df2.show()
    users_df2.printSchema()

    spark.stop()
    println("Apache Spark Application finished ...")
  }
}
