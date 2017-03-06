package com.sapient.data.validation

import com.sapient.data.validation.rule.RuleApi
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Row, SQLContext}
import org.apache.spark.{SparkConf, SparkContext}
import org.scalatest.{BeforeAndAfterAll, FunSuite}


/**
  * Created by sku329 on 3/6/2017.
  */
class ValidationTest extends FunSuite with BeforeAndAfterAll {

  import com.sapient.data.validation.ValidatorApi._

  val sparkConf = new SparkConf().setMaster("local[*]").setAppName("ValidationApp")
  val sc = new SparkContext(sparkConf)
  val sqlContext = new SQLContext(sc)
  test("Apply user define rule to a CSV") {
    val firstRule = new RuleApi {
      override def apply(v1: Row): Record = {
        val book = v1.get(1).toString.toInt
        if (book > 0)
          ValidRecord(v1)
        else
          InvalidRecord(v1, Map(1 -> RejectionReason("Book value should be greater than zero", "Book rule")))
      }
    }
    val csvData = sqlContext.read
      .format("com.databricks.spark.csv")
      .option("header", "true")
      .load("src\\test\\resources\\test.txt").rdd
    val result: RDD[Record] = csvData.validate(firstRule)
    val validRecord = result.collect { case record: ValidRecord => record }.collect()
    val invalidRecord = result.collect { case record: InvalidRecord => record }.collect()

    assert(validRecord.length == 5)
    assert(invalidRecord.length == 2)
  }

  override protected def afterAll(): Unit = {
    sc.stop()
  }
}
