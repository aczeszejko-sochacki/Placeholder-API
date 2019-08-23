package placeholder.url

import org.scalatest._
import org.scalamock.scalatest.MockFactory
import java.lang.{ StringIndexOutOfBoundsException => OutOfBounds }
import java.net.{ UnknownHostException => Unknown, MalformedURLException => Malformed }

class FetchFromUrlSpec extends FlatSpec with Matchers {
  val correctUrl = "https://jsonplaceholder.typicode.com/posts/1"
  val uncorrectUrl = "https://"
  val uncorrectPath = "https://blackhole"
  val uncorrectProtocol = ""
  
  val mockUrlFetcher = new AnyRef with UrlFetcher
  
  "An UrlFetcher.fetchUrlAsString" should "return content as string given correct url" in {
    mockUrlFetcher.fetchUrlAsString(correctUrl) shouldBe a [String]
  }

  it should "throw an StringIndexOutOfBoundsException given uncorrect URL" in {
    assertThrows[OutOfBounds] {
      mockUrlFetcher.fetchUrlAsString(uncorrectUrl)
    }
  }

  it should "throw a MalformedURLException given uncorrect protocol" in {
    assertThrows[Unknown] {
      mockUrlFetcher.fetchUrlAsString(uncorrectPath)
    }
  }

  it should "throw an UnknownHostException given uncorrect protocol" in {
    assertThrows[Malformed] {
      mockUrlFetcher.fetchUrlAsString(uncorrectProtocol)
    }
  }
}