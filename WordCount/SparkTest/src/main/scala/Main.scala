import org.apache.spark.{SparkConf, SparkContext}

object Main {
  def main(args: Array[String]): Unit = {
    val x = "C:\\Users\\session\\Desktop\\abc.txt"
    val conf = new SparkConf().setAppName("HelloSpark").setMaster("local")
    val sc = new SparkContext(conf)
    val y = sc.textFile(x).cache();
    val counts = y.flatMap(line => line.split(" "))
      .map(word => (word, 1))
      .reduceByKey(_ + _)
    counts.saveAsTextFile("C:\\Users\\session\\Desktop\\sparkOutput" + java.util.UUID.randomUUID().toString)
//    counts.foreach(println)
    sc  .stop()
  }
}
