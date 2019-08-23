package placeholder.actors

import akka.actor._
import akka.event.Logging
import scala.language.postfixOps

class DownloadManagerActor(val urls: List[String], val savePath: String) extends Actor {
  val log = Logging(context.system, this)

  val actorChildren = urls map (x => context.actorOf(DownloadActor.props))
  val immMapActorUrl = actorChildren zip urls toMap

  // We maintain the map of children which have not downloaded their corresponding urls yet
  // It is important to have one to one relation between the urls and actors
  var activeActors = scala.collection.mutable.Map[ActorRef, String]() ++ immMapActorUrl

  def receive = {
    case DownloadManagerActor.DownloadAllUrls =>
      if (activeActors.isEmpty) context.system.terminate
      else for ((actorRef, url) <- activeActors) actorRef ! DownloadActor.Download(url, savePath, url.split("/").last)

    case DownloadManagerActor.UrlDownloaded(url) => log.info(s"Successfully downloaded from $url"); activeActors -= sender; context.stop(sender)

    case DownloadManagerActor.Error(e) => log.error(s"Could not download because of $e"); activeActors -= sender; context.stop(sender)
  }
}

object DownloadManagerActor {
  def props(urls: List[String], savePath: String) = Props(new DownloadManagerActor(urls, savePath))

  case object DownloadAllUrls
  case class UrlDownloaded(url: String)
  case class Error(e: Exception)
}