package util

import java.io.FileWriter

object FileUtil {

  def writeToFile(fileName: String, content: String) {
    val writer = new FileWriter(fileName)
    try {
      writer.write(content)
    } finally {
      writer.flush()
      writer.close()
    }
  }

}
