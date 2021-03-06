package comy.model

import xitrum.I18n
import xitrum.validator._

case class Key(value: String) {
  val ALLOW_CHARS = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM1234567890_-"

  def v(i18n: I18n): Option[String] = {
    val invalidCharIncluded = value.exists(c => ALLOW_CHARS.indexOf(c) == -1)
    val erroro = if (invalidCharIncluded) Some(i18n.t("Key contains invalid character")) else None

    Required.message("Key", value) orElse
    MaxLength(32).message("Key", value) orElse
    erroro
  }
}
