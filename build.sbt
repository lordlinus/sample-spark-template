

name := "sample-spark-template"

version := "1.0"

scalaVersion := "2.11.5"

val sparkVersion = "2.0.0"
val scalacheckVersion = "1.12.5"
val sparkTestingVersion = "2.0.0_0.4.4"
val scalatestVersion = "2.2.6"

libraryDependencies ++= List(
                              ("org.apache.spark" %% "spark-core" % sparkVersion % "provided").
                                exclude("org.mortbay.jetty", "servlet-api").
                                exclude("commons-beanutils", "commons-beanutils-core").
                                exclude("commons-collections", "commons-collections").
                                exclude("commons-logging", "commons-logging").
                                exclude("com.esotericsoftware.minlog", "minlog"),
                              "org.apache.spark" %% "spark-sql" % sparkVersion % "provided",
                              "org.apache.spark" %% "spark-mllib" % sparkVersion % "provided",
                              "org.apache.spark" %% "spark-hive" % sparkVersion % "provided",
                              "org.scalatest" %% "scalatest" % scalatestVersion % "test",
                              "org.scalacheck" %% "scalacheck" % scalacheckVersion % "test",
                              "junit" % "junit" % "4.11" % "test",
                              "com.holdenkarau" %% "spark-testing-base" % sparkTestingVersion % "test"
                            )
libraryDependencies += "com.typesafe" % "config" % "1.3.0"

fork := true
parallelExecution in Test := false


javaOptions ++= Seq("-Xms512M", "-Xmx2048M", "-XX:MaxPermSize=2048M", "-XX:+CMSClassUnloadingEnabled", "-Djna.nosys=true")
javacOptions ++= Seq("-source", "1.8", "-target", "1.8")

scalacOptions ++= List(
                        "-deprecation",
                        "-encoding", "UTF-8",
                        "-feature",
                        "-language:existentials",
                        "-language:higherKinds",
                        "-language:implicitConversions",
                        "-unchecked",
                        "-Xfatal-warnings",
                        //  "-Xlint",
                        "-Yno-adapted-args",
                        "-Ywarn-dead-code",
                        "-Ywarn-numeric-widen",
                        "-Ywarn-value-discard", // fails with @sp on Unit
                        "-Xfuture"
                      )

crossScalaVersions := List(scalaVersion.value)


testOptions in Test += Tests.Argument("-oF")

fork in Test := true