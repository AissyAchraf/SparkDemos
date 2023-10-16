import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.types.IntegerType

object create_dataframe_from_mysql_table {
  def main(args: Array[String]): Unit = {

    println("Apache Spark Application started ...")

    val spark = SparkSession.builder()
      .appName("Create DataFrame form CSV File")
      .master("local[*]")
      .getOrCreate()

    spark.sparkContext.setLogLevel("ERROR")


    val mysql_db_driver_class = "com.mysql.jdbc.Driver"
    val table_name = "user_detail"
    val host_name = "localhost"
    val port_num = 3306
    val user_name = "root"
    val password = ""
    val db_name = "spark_demos"

    val mysql_select_query =  "(select * from " + table_name + ") as users"

    val mysql_jdbc_url = "jdbc:mysql://"+host_name+":"+port_num+"/"+db_name+"?serverTimezone=Europe/Rome"

    val users_df = spark.read
      .format("jdbc")
      .option("url", mysql_jdbc_url)
      .option("driver", mysql_db_driver_class)
      .option("dbtable", mysql_select_query)
      .option("user", user_name)
      .option("password", password)
      .load()

    users_df.show()

    spark.stop()
    println("Apache Spark Application finished ...")
  }
}
