val javaVmArgs: List[String] = {
  import scala.collection.JavaConverters._
  java.lang.management.ManagementFactory.getRuntimeMXBean.getInputArguments.asScala.toList
}

val baseSettings = Seq(
  scalaVersion := "$scala_version$",
  scalacOptions ++= (
    "-deprecation" ::
    "-unchecked" ::
    "-language:existentials" ::
    "-language:higherKinds" ::
    "-language:implicitConversions" ::
    Nil
  ),
  javaOptions ++= javaVmArgs.filter(
    a => Seq("-Xmx", "-Xms", "-XX", "-Dsbt.log.noformat").exists(a.startsWith)
  ),
  shellPrompt := { state =>
    val branch = if(file(".git").exists){
      sys.process.Process("git branch").lineStream_!.find{_.head == '*'}.map{_.drop(1)}.getOrElse("")
    }else ""
    Project.extract(state).currentRef.project + branch + " > "
  },
  fullResolvers ~= {_.filterNot(_.name == "jcenter")},
  resolvers ++= Seq(Opts.resolver.sonatypeReleases)
)

lazy val root = Project(
  "$name$", file(".")
).enablePlugins(PlayScala).settings(
  baseSettings,
  libraryDependencies ++= (
    guice ::
    Nil
  )
)

