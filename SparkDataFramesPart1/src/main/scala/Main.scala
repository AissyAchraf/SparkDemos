import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Row, SQLContext, SparkSession, types}
import org.apache.spark.sql.types.{DoubleType, IntegerType, StringType, StructField, StructType}
import org.apache.spark.{SparkConf, SparkContext}

case class User(user_id : Int, user_name : String, user_city : String)

object Main {
  def main(args: Array[String]): Unit = {
    println("Application started ...")
    val spark = SparkSession
      .builder()
      .appName("My Spark App")
      .master("local[*]")
      .getOrCreate()

    println("Approach 1:")
    val users = List((1, "Achraf Aissy", "Casablanca"),
      (2, "Aymane Elalami", "Casablanca"),
      (3, "Kamal Marouane", "Grenoble"),
      (4, "Mehdi Azouni", "Lyon"))

    val users_rdd = spark.sparkContext.parallelize(users)
    val users_df = spark.createDataFrame(users_rdd)
    users_df.show()

    println("Approach 2:")
    val users_rows = Seq(
      Row(1, "Achraf Aissy", "Casablanca"),
      Row(2, "Aymane Elalami", "Casablanca"),
      Row(3, "Kamal Marouane", "Grenoble"),
      Row(4, "Mehdi Azouni", "Lyon")
    )

    val schema = StructType(Array(
      StructField("user_ID", IntegerType, false),
      StructField("user_name", StringType, false),
      StructField("user_city", StringType, false),
    ))

    val users_df2 = spark.createDataFrame(spark.sparkContext.parallelize(users_rows), schema)
    users_df2.show()

    println("Approach 3:")
    val users3 = Seq(
      User(1, "Achraf Aissy", "Casablanca"),
      User(2, "Aymane Elalami", "Casablanca"),
      User(3, "Kamal Marouane", "Grenoble"),
      User(4, "Mehdi Azouni", "Lyon")
    )

    val users_rdd2 = spark.sparkContext.parallelize(users3)
    val users_df3 = spark.createDataFrame(users_rdd2)
    users_df3.show()

    spark.stop()
  }
}
