package it.pizzafaenza.menu.json

import io.circe.Json
import io.circe.parser.parse
import org.scalajs.dom

import scala.concurrent.Future
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue

object BrowserJsonReader extends JsonReader:
  override def read(path: String): Future[Json] =
    dom.fetch(path)
      .toFuture
      .flatMap(_.text().toFuture)
      .map { text =>
        parse(text) match
          case Right(json) => json
          case Left(error) =>
            throw new Exception(s"Failed to parse JSON: $error")
      }
