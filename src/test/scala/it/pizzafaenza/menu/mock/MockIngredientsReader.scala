package it.pizzafaenza.menu.mock

import io.circe.Json
import it.pizzafaenza.menu.json.JsonReader
import scala.concurrent.Future

object MockIngredientsReader extends JsonReader:
  override def read(path: String): Future[Json] =
    val mockJsonString =
      """
        |[
        |    {
        |        "id_ingrediente": "0",
        |        "nome_italiano": "impasto pizza",
        |        "nome_inglese": "white pizza",
        |        "allergeni": "1",
        |        "id": "1",
        |        "tipo": "Null"
        |    },
        |    {
        |        "id_ingrediente": "1",
        |        "nome_italiano": "salsa di pomodoro",
        |        "nome_inglese": "tomato sauce",
        |        "allergeni": "1",
        |        "id": "1",
        |        "tipo": "Null"
        |    },
        |    {
        |        "id_ingrediente": "2",
        |        "nome_italiano": "mozzarella",
        |        "nome_inglese": "mozzarella cheese",
        |        "allergeni": "7",
        |        "id": "7",
        |        "tipo": "Latticini"
        |    },
        |    {
        |        "id_ingrediente": "3",
        |        "nome_italiano": "crudo di Parma",
        |        "nome_inglese": "prosciutto",
        |        "allergeni": "1",
        |        "id": "1",
        |        "tipo": "Null"
        |    },
        |    {
        |        "id_ingrediente": "4",
        |        "nome_italiano": "funghi",
        |        "nome_inglese": "champignon mushrooms",
        |        "allergeni": "1",
        |        "id": "1",
        |        "tipo": "Null"
        |    },
        |    {
        |        "id_ingrediente": "69",
        |        "nome_italiano": "insalata",
        |        "nome_inglese": "salad",
        |        "allergeni": "1",
        |        "id": "1",
        |        "tipo": "Null"
        |    },
        |    {
        |        "id_ingrediente": "32",
        |        "nome_italiano": "olive nere",
        |        "nome_inglese": "black olive",
        |        "allergeni": "1",
        |        "id": "1",
        |        "tipo": "Null"
        |    },
        |    {
        |        "id_ingrediente": "56",
        |        "nome_italiano": "pomodori calabresi",
        |        "nome_inglese": "dried tomatoes",
        |        "allergeni": "1",
        |        "id": "1",
        |        "tipo": "Null"
        |    },
        |    {
        |        "id_ingrediente": "15",
        |        "nome_italiano": "pomodorini",
        |        "nome_inglese": "cherry tomatoes",
        |        "allergeni": "1",
        |        "id": "1",
        |        "tipo": "Null"
        |    },
        |    {
        |        "id_ingrediente": "14",
        |        "nome_italiano": "radicchio",
        |        "nome_inglese": "radicchio",
        |        "allergeni": "1",
        |        "id": "1",
        |        "tipo": "Null"
        |    },
        |    {
        |        "id_ingrediente": "30",
        |        "nome_italiano": "rucola",
        |        "nome_inglese": "bed of arugula",
        |        "allergeni": "1",
        |        "id": "1",
        |        "tipo": "Null"
        |    },
        |    {
        |        "id_ingrediente": "39",
        |        "nome_italiano": "tonno",
        |        "nome_inglese": "tuna",
        |        "allergeni": "3",
        |        "id": "3",
        |        "tipo": "Pesce"
        |    }
        |]
        |""".stripMargin

    Future.successful(
      io.circe.parser.parse(mockJsonString).getOrElse(Json.Null)
    )
