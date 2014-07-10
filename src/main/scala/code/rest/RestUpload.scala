package code.rest

import net.liftweb.http.rest.RestHelper

object RestUpload extends RestHelper {

  serve {
    case "upload" :: Nil Post req =>
      for {
        bodyBytes <- req.body
      } yield {
        val content = bodyBytes.map(_.toChar)
        <info>POST Received
          {bodyBytes.length}
          bytes
          <br/>{content}
        </info>
      }
    case "upload" :: Nil Get req =>
      for {
        bodyBytes <- req.body
      } yield <info>GET Received
        {bodyBytes.length}
        bytes</info>
  }

}