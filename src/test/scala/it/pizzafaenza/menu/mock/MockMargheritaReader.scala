package it.pizzafaenza.menu.mock

import io.circe.Json
import it.pizzafaenza.menu.json.JsonReader
import scala.concurrent.{ExecutionContext, Future}

object MockMargheritaReader extends JsonReader:
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
        |    }
        |]
        |""".stripMargin

    Future.successful(
      io.circe.parser.parse(mockJsonString).getOrElse(Json.Null)
    )
