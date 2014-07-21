package code.lib

import net.liftweb.http.SessionVar

object SessionOptions extends SessionVar[UserOptions](new UserOptions)
