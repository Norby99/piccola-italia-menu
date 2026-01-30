package it.pizzafaenza.menu.extraToppings

import io.circe.{Decoder, HCursor}
import it.pizzafaenza.menu.ingredients.{Name}
import it.pizzafaenza.menu.json.JsonReader
import it.pizzafaenza.menu.menu.MenuDishCollection
import it.pizzafaenza.menu.pizza.{Pizza, PizzeCollection}

import scala.concurrent.{ExecutionContext, Future}

class ExtraToppingsCollection(jsonReader: JsonReader)(implicit
    ec: ExecutionContext
):
  private val DBPath = "data/aggiunte.json"

  given Decoder[ExtraTopping] = new Decoder[ExtraTopping]:
    final def apply(c: HCursor): Decoder.Result[ExtraTopping] =
      for
        id <- c.downField("id_aggiunta").as[Int]
        nameItalian <- c.downField("nome_aggiunta").as[String]
        nameEnglish <- c.downField("nome_inglese").as[String]
        price <- c.downField("prezzo").as[String].map(_.toDouble)
      yield ExtraTopping(id, Name(nameItalian, nameEnglish), price)

  def getExtraTopping: Future[List[ExtraTopping]] =
    jsonReader.read(DBPath).map { content =>
      content.as[List[ExtraTopping]] match
        case Right(l) => l.sortBy(_.id)
        case Left(error) =>
          throw new Exception(s"Failed to decode Extra Topping: $error")
    }
