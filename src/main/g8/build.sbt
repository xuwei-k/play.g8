val originalJvmOptions = sys.process.javaVmArguments.filter(
  a => Seq("-Xmx", "-Xms", "-XX").exists(a.startsWith)
)

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
  javaOptions ++= originalJvmOptions,
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

