package it.pizzafaenza.menu.mock

import io.circe.Json
import it.pizzafaenza.menu.json.JsonReader

import scala.concurrent.Future

object MockExtraToppingsReader extends JsonReader:
  override def read(path: String): Future[Json] =
    val mockJsonString =
      """
        |[
        |    {
        |        "id_aggiunta": "1",
        |        "nome_aggiunta": "Doppio impasto",
        |        "nome_inglese": "Double dough",
        |        "prezzo": "1.5"
        |    },
        |    {
        |        "id_aggiunta": "2",
        |        "nome_aggiunta": "Mozzarella di Bufala",
        |        "nome_inglese": "Buffalo mozzarella",
        |        "prezzo": "2.5"
        |    }
        |]
        |""".stripMargin

    Future.successful(
      io.circe.parser.parse(mockJsonString).getOrElse(Json.Null)
    )
