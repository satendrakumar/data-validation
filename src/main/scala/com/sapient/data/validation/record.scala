package com.sapient.data.validation

import org.apache.spark.sql.Row

sealed trait Record

case class ValidRecord(row: Row) extends Record

case class InvalidRecord(row: Row, reasons: Map[Int, RejectionReason]) extends Record

case class RejectionReason(message: String, ruleName: String)