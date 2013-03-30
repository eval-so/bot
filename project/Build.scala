import sbt._
import Keys._

object ProjectBuild extends Build {
  lazy val root = Project("root", file(".")).dependsOn(uri("git://github.com/eval-gd/minibcs"))
}
