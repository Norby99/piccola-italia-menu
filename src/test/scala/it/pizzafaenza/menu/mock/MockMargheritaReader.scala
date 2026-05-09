package it.pizzafaenza.menu.mock

import io.circe.Json
import it.pizzafaenza.menu.json.JsonReader
import scala.concurrent.Future

object MockMargheritaReader extends JsonReader:
  override def read(path: String): Future[Json] =
    val mockJsonString =
      """
        |[
        |    {
        |        "id":"1",
        |        "name":"Margherita",
        |        "category":"Pizze classiche",
        |        "price":"4.5",
        |        "dough":"impasto normale",
        |        "toppings_italian":"salsa di pomodoro,mozzarella",
        |        "toppings_english":"tomato sauce,mozzarella cheese",
        |        "allergens":"Soia,Glutine,Latticini"
        |    }
        |]
        |""".stripMargin

    Future.successful(
      io.circe.parser.parse(mockJsonString).getOrElse(Json.Null)
    )
