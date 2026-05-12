package it.pizzafaenza.menu.utils

import scala.scalajs.js
import scala.scalajs.js.Dynamic.global

object AppConfig:
  private val fallbackLangUrl =
    "http://localhost:100/request/menupizze/language_setting/index.php"

  val langPollingUrl: String =
    if js.typeOf(global.__ENV__) != "undefined" &&
      js.typeOf(global.__ENV__.LANG_POLLING_URL) != "undefined"
    then
      global.__ENV__.LANG_POLLING_URL.asInstanceOf[String]
    else
      fallbackLangUrl
