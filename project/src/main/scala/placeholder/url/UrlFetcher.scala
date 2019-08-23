package placeholder.url

import scala.io.Source

trait UrlFetcher {
  // Assuming the content is short; otherwise we cannot use mkString
  def fetchUrlAsString(url: String): String = Source.fromURL(url).mkString
}