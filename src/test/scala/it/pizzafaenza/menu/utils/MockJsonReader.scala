package it.pizzafaenza.menu.utils

import io.circe.Json
import it.pizzafaenza.menu.json.JsonReader
import scala.concurrent.{ExecutionContext, Future}

object MockJsonReader extends JsonReader:
  override def read(path: String): Future[Json] =
    val mockJsonString =
      """
        |[
        |    {
        |        "id": "1",
        |        "nomePizza": "Margherita",
        |        "nome_tipo": "Pizze classiche",
        |        "prezzo": "4",
        |        "ingredienti": "1,2"
        |    },
        |    {
        |        "id": "2",
        |        "nomePizza": "Prosciutto crudo",
        |        "nome_tipo": "Pizze classiche",
        |        "prezzo": "6",
        |        "ingredienti": "1,2,3"
        |    }
        |]
        """.stripMargin

    Future.successful(
      io.circe.parser.parse(mockJsonString).getOrElse(Json.Null)
    )
