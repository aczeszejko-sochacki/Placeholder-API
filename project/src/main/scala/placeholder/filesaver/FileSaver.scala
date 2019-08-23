package placeholder.filesaver

import java.io._

trait FileSaver {
  def SaveAsJson(content: String, path: String, name: String) = {
    val fullFilePath = new PrintWriter(new File(path + "/" + name + ".json"))
    fullFilePath.write(content)
    fullFilePath.close
  }
}