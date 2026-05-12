package it.pizzafaenza.menu.language

import io.circe.parser.decode
import io.circe.generic.auto.*
import it.pizzafaenza.menu.utils.AppConfig
import org.scalajs.dom
import org.scalajs.dom.Fetch

import scala.util.{Failure, Success}
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue

case class LanguageResponse(lang: String)

object LanguagePoller:
  private val langUrl = AppConfig.langPollingUrl

  def start(intervalMs: Int = 1000): Unit =
    dom.window.setInterval(() => poll(), intervalMs.toDouble)

  private def poll(): Unit =
    Fetch.fetch(langUrl).toFuture
      .flatMap(_.text().toFuture)
      .onComplete:
        case Success(json) => handleResponse(json)
        case Failure(ex) =>
          dom.console.error(s"Language fetch error: ${ex.getMessage}")

  private def handleResponse(json: String): Unit =
    decode[LanguageResponse](json) match
      case Right(r) =>
        val newLang = if r.lang == "it" then Italian else English
        if CurrentLanguage.currentLang.now() != newLang then
          CurrentLanguage.currentLang.set(newLang)
      case Left(err) =>
        dom.console.error(s"Language JSON parse error: $err")
