package placeholder.filesaver

import org.scalatest._
import java.io._
import scala.reflect.io.Directory


class FileSaverSpec extends FlatSpec {
  val mockFileSaver = new AnyRef with FileSaver

  "A FileSaver" should "save file given correct path" in {
    assertResult(Array(new File("./tmp/a.json"))) {
      new File("./tmp").mkdir
      mockFileSaver.SaveAsJson("", "./tmp", "a")
      new File("./tmp").listFiles
    }

    // Need to rm .../tmp/* for future
    val directory = new Directory(new File("./tmp"))
    directory.deleteRecursively()
  }

  it should "throw FileNotFoundException given uncorrect path" in {
    assertThrows[FileNotFoundException] {
      mockFileSaver.SaveAsJson("", "top secret", "a")
    }
  }
}