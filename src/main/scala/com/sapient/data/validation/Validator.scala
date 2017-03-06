package com.sapient.data.validation

import com.sapient.data.validation.rule.RuleApi
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.Row

object ValidatorApi {

  implicit class Validator(val rdd: RDD[Row]) {
    def validate(rule: RuleApi): RDD[Record] = rdd map rule
  }

}