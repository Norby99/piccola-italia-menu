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
        |        "id":"1",
        |        "name":"Insalatona",
        |        "price":"9",
        |        "toppings_italian":"radicchio,pomodorini,rucola,olive nere,tonno,pomodori calabresi,insalata",
        |        "toppings_english":"radicchio,cherry tomatoes,bed of arugula,black olive,tuna,dried tomatoes,salad",
        |        "allergens":"Pesce"
        |    }
        |]
        |""".stripMargin

    Future.successful(
      io.circe.parser.parse(mockJsonString).getOrElse(Json.Null)
    )
