package placeholder.actors

import akka.actor._

import placeholder.url.UrlFetcher
import placeholder.filesaver.FileSaver

class DownloadActor extends Actor with UrlFetcher with FileSaver {
  def receive = downloading

  def downloading: Actor.Receive = {
    case DownloadActor.Download(url, savePath, fileName) => 
      try {
          SaveAsJson(fetchUrlAsString(url), savePath, fileName)
          sender ! DownloadManagerActor.UrlDownloaded(url)
        }
      catch {
        case e: Exception => sender ! DownloadManagerActor.Error(e)
      }
  }
}

object DownloadActor {
  def props = Props(new DownloadActor)

  case class Download(url: String, savePath: String, fileName: String)
}