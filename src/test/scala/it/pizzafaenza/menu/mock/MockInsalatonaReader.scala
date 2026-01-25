package it.pizzafaenza.menu.mock

import io.circe.Json
import it.pizzafaenza.menu.json.JsonReader

import scala.concurrent.Future

object MockInsalatonaReader extends JsonReader:
  override def read(path: String): Future[Json] =
    val mockJsonString =
      """
        |[
        |    {
        |        "id": "1",
        |        "nomeInsalata": "Insalatona",
        |        "prezzo": "9",
        |        "ingredienti": "69,32,56,15,14,30,39"
        |    }
        |]
        |""".stripMargin

    Future.successful(
      io.circe.parser.parse(mockJsonString).getOrElse(Json.Null)
    )
