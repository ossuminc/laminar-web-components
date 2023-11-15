import com.typesafe.sbt.packager.Keys.maintainer
import sbt.*
import sbt.Keys.{description, developers, homepage, isSnapshot, licenses, organization, organizationHomepage, organizationName, pomIncludeRepository, publishMavenStyle, publishTo, scmInfo}
import sbtdynver.DynVerPlugin.autoImport.{dynverSeparator, dynverSonatypeSnapshots}

import java.net.URI
object Publish {

  def withMaven(p: Project): Project = {
    p.settings(
      ThisBuild / dynverSonatypeSnapshots := true,
      ThisBuild / dynverSeparator := "-",
      maintainer := "reid@ossum.biz",
      organization := "com.reactific",
      organizationName := "Ossum Inc.",
      organizationHomepage := Some(url("https://riddl.tech")),
      scmInfo := Some(
        ScmInfo(
          url("https://github.com/reactific/riddl"),
          "scm:git:git://github.com/reactific/riddl.git"
        )
      ),
      developers := List(
        Developer(
          id = "reid-spencer",
          name = "Reid Spencer",
          email = "reid@reactific.com",
          url = url("https://riddl.tech")
        )
      ),
      description :=
        """RIDDL is a language and toolset for specifying a system design using ideas from
          |DDD, reactive architecture, distributed systems patterns, and other software
          |architecture practices.""".stripMargin,
      licenses := List(
        "Apache License, Version 2.0" ->
          URI.create("https://www.apache.org/licenses/LICENSE-2.0").toURL
      ),
      homepage := Some(url("https://ossuminc.com/")),

      // Remove all additional repository other than Maven Central from POM
      pomIncludeRepository := { _ => false },
      publishTo := {
        val nexus = "https://oss.sonatype.org/"
        if (isSnapshot.value) {
          Some("snapshots" at nexus + "content/repositories/snapshots")
        } else {
          Some("releases" at nexus + "service/local/staging/deploy/maven2")
        }
      },
      publishMavenStyle := true
    )
  }


}
