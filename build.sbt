import AssemblyKeys._

assemblySettings

name := "algebird_aggregator_test"

organization := ""

scalaVersion := "2.10.2"

scalacOptions ++= Seq("-deprecation", "-feature", "-language:_")

javacOptions ++= Seq("-source", "1.6", "-target", "1.6")


libraryDependencies <++= scalaVersion.apply(scalaVersion => {
  val hadoopVersion = "2.0.0-mr1-cdh4.3.0"
  val scaldingVersion = "0.9.0-STEPHAN"
  val algebirdVersion = "0.2.1-STEPHAN"
  Seq(
    "org.apache.hadoop"      %  "hadoop-client"     % hadoopVersion    % "provided",
    "org.apache.hadoop"      %  "hadoop-core"       % hadoopVersion    % "provided",
    "com.twitter"            %% "scalding-core"     % scaldingVersion,
    "com.twitter"            %% "algebird-core"     % algebirdVersion,
    "org.specs2"             %% "specs2"            % "2.1.1"          % "test"
  )
})

mergeStrategy in assembly <<= (mergeStrategy in assembly)(old => {
  case PathList("org", "slf4j", xs @ _ *)                          => MergeStrategy.first
  case PathList("META-INF", "sisu", "javax.inject.Named", xs @ _*) => MergeStrategy.first
  case PathList("about.html", xs @ _*)                             => MergeStrategy.discard
  case PathList("com", "esotericsoftware", "minlog", xs @ _ *)     => MergeStrategy.first
  case "project.clj"                                               => MergeStrategy.discard
  case PathList("org", "objectweb", "asm", xs @_*)                 => MergeStrategy.discard
  case PathList("org", "apache", "commons", "logging", xs @ _ *)   => MergeStrategy.discard
  case "rootdoc.txt"                                               => MergeStrategy.discard
  case x                                                           => old(x)
})
