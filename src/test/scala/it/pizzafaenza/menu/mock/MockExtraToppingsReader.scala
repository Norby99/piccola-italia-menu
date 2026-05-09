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
        |        "id": "1",
        |        "name_italian": "Doppio impasto",
        |        "name_english": "Double dough",
        |        "price": "1.5"
        |    },
        |    {
        |        "id": "2",
        |        "name_italian": "Mozzarella di Bufala",
        |        "name_english": "Buffalo mozzarella",
        |        "price": "2.5"
        |    }
        |]
        |""".stripMargin

    Future.successful(
      io.circe.parser.parse(mockJsonString).getOrElse(Json.Null)
    )
