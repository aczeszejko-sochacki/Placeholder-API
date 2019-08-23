import akka.actor._
import scala.concurrent.duration._
import scala.language.postfixOps
import scala.concurrent.ExecutionContext.Implicits.global
import java.io._

import placeholder.actors._
import placeholder.filesaver.FileSaver

// Choose this one in sbt for download the posts to resources directory
object Download extends App {
  val ourSystem = ActorSystem("placeholder")

  val urlsToDownload = List.tabulate(100)(x => s"https://jsonplaceholder.typicode.com/posts/${x + 1}")

  val downloadManager: ActorRef =
    ourSystem.actorOf(DownloadManagerActor.props(urlsToDownload, "src/main/resources"), name = "manager")

  // Send request periodically as the communication is unreliable
  ourSystem.scheduler.schedule(0 second, 1 second, downloadManager, DownloadManagerActor.DownloadAllUrls)
}

// Choose this one in sbt to remove saved posts from the resources directory
object Remove extends App {
  val d = new File("src/main/resources")

  d.listFiles map (_.delete)
}