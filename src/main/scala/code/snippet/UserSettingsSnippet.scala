package code.snippet


import code.lib.UserOptions
import net.liftweb.http.SHtml


class UserSettingsSnippet {

  def render= <div>{SHtml.ajaxCheckbox(UserOptions.doVacuityChecks, v => UserOptions.doVacuityChecks = v)}</div>
}
