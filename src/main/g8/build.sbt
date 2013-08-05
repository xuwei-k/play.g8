val baseSettings = seq(
  scalaVersion := "$scala_version$",
  scalacOptions := Seq("-language:_", "-deprecation", "-unchecked", "-Xlint"),
  resolvers ++= Seq(Opts.resolver.sonatypeReleases)
)

val main = play.Project("$name$", "$version$", Seq()).settings(
  baseSettings : _*
)
