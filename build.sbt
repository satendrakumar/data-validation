name := "data-validation"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies ++=Seq(
  "org.apache.spark" %% "spark-core" % "1.6.2",
  "org.apache.spark" %% "spark-sql" % "1.6.2",
  "com.databricks" %% "spark-csv" % "1.5.0",
  "org.scalatest" %% "scalatest" % "3.0.1"
)
    