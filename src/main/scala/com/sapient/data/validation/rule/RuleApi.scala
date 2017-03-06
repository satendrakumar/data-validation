package com.sapient.data.validation.rule

import com.sapient.data.validation.Record
import org.apache.spark.sql.Row

/**
  * User need to implement this trait to define the validation rule.
  */
trait RuleApi extends (Row => Record) with Serializable

