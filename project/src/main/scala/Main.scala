import akka.actor._
import scala.concurrent.duration._
import scala.language.postfixOps
import scala.concurrent.ExecutionContext.Implicits.global

import placeholder.actors._
import placeholder.filesaver.FileSaver

object Main extends App {
  val ourSystem = ActorSystem("placeholder")

  val urlsToDownload = List.tabulate(100)(x => s"https://jsonplaceholder.typicode.com/posts/${x + 1}")

  val downloadManager: ActorRef =
    ourSystem.actorOf(DownloadManagerActor.props(urlsToDownload, "src/main/resources"), name = "manager")

  // Send request periodically as the communication is unreliable
  ourSystem.scheduler.schedule(0 second, 1 second, downloadManager, DownloadManagerActor.DownloadAllUrls)
}