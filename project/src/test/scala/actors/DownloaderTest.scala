package placeholder.actors

import akka.actor._
import akka.testkit._
import org.scalatest._

class DownloadActorTest extends TestKit(ActorSystem("Test"))
  with FlatSpecLike
  with ImplicitSender
  with BeforeAndAfterAll {

  override def afterAll: Unit = {
    TestKit.shutdownActorSystem(system)
  }

  val downloadActor = system.actorOf(DownloadActor.props)

  "A DownloadActor" should "send UrlDownloaded message given correct url and path" in {
    val url = "https://jsonplaceholder.typicode.com/posts/1"

    downloadActor ! DownloadActor.Download(url, ".", "a")
        
    expectMsg(DownloadManagerActor.UrlDownloaded(url))
  }

  it should "send Error message given wrong url or path" in {
    downloadActor ! DownloadActor.Download("https://avemovm", ".", "a")

    expectMsgType[DownloadManagerActor.Error]
  }
}
