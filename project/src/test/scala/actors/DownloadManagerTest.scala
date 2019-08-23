package placeholder.actors

import akka.actor._
import akka.testkit._
import org.scalatest._

class DownloadManagerActorTest extends TestKit(ActorSystem("Test"))
  with FlatSpecLike
  with ImplicitSender
  with BeforeAndAfterAll {

  override def afterAll: Unit = {
    TestKit.shutdownActorSystem(system)
  }

  val url = "https://jsonplaceholder.typicode.com/posts/1"
  val downloadManagerActor = system.actorOf(DownloadManagerActor.props(List(), ""))

  "A DownloadManagerActor" should "send no message having received UrlDownloaded message" in {
    downloadManagerActor ! DownloadManagerActor.UrlDownloaded(url)
    expectNoMessage
  }

  it should "should send no message having received Error message" in {
    downloadManagerActor ! DownloadManagerActor.Error(new Exception)
    expectNoMessage
  }
}