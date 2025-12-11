package it.pizzafaenza.menu.mock

import io.circe.Json
import it.pizzafaenza.menu.json.JsonReader

import scala.concurrent.Future

object MockGamberettiReader extends JsonReader:
  override def read(path: String): Future[Json] =
    val mockJsonString =
      """
        |[
        |    {
        |        "id_ingrediente": "50",
        |        "nome_italiano": "gamberetti",
        |        "nome_inglese": "shrimps",
        |        "allergeni": "3",
        |        "id": "3",
        |        "tipo": "Pesce"
        |    }
        |]
        |""".stripMargin

    Future.successful(
      io.circe.parser.parse(mockJsonString).getOrElse(Json.Null)
    )
