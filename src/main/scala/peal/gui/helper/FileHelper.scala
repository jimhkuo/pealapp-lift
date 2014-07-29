package peal.gui.helper

import java.io.FileWriter

object FileHelper {

  def writeToFile(fileName: String, result: String) {
    val writer = new FileWriter(fileName)
    try {
      writer.write(result)
    } finally {
      writer.flush()
      writer.close()
    }
  }
}
