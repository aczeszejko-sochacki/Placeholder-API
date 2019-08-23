package placeholder.actors

import akka.actor._

import placeholder.url.UrlFetcher
import placeholder.filesaver.FileSaver

class DownloadActor extends Actor with UrlFetcher with FileSaver {
  def receive = downloading

  def downloading: Actor.Receive = {
    case DownloadActor.DownloadAndSave(url, savePath, fileName) => try {
      SaveAsJson(fetchUrlAsString(url), savePath, fileName)
      sender ! "message todo in sender"
    }
    catch {
      case e: Exception => sender ! "message todo in sender"
    }
  }
}

object DownloadActor {
  def props = Props(new DownloadActor)

  case class DownloadAndSave(url: String, savePath: String, fileName: String)
}