scalacOptions ++= (
  "-deprecation" ::
  "-unchecked" ::
  "-Xlint" ::
  "-language:existentials" ::
  "-language:higherKinds" ::
  "-language:implicitConversions" ::
  "-Yno-adapted-args" ::
  Nil
)

fullResolvers ~= {_.filterNot(_.name == "jcenter")}
  
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "$play_version$")
