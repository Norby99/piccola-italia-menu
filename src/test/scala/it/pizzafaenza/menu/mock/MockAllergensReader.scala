package it.pizzafaenza.menu.mock

import io.circe.Json
import it.pizzafaenza.menu.json.JsonReader
import scala.concurrent.Future

import scala.concurrent.Future

object MockAllergensReader extends JsonReader:
  override def read(path: String): Future[Json] =
    val mockJsonString =
      """
        |[
        |   {
        |       "id":"1",
        |       "name":"Null"
        |   },
        |   {
        |       "id":"2",
        |       "name":"Uova"
        |   },
        |   {
        |       "id":"3",
        |       "name":"Pesce"
        |   },
        |   {
        |       "id":"7",
        |       "name":"Latticini"
        |   }
        |]
        |""".stripMargin

    Future.successful(
      io.circe.parser.parse(mockJsonString).getOrElse(Json.Null)
    )
