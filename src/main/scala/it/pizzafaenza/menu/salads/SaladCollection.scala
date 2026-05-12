package it.pizzafaenza.menu.salads

import it.pizzafaenza.menu.json.JsonReader
import io.circe.*
import it.pizzafaenza.menu.allergens.Allergen
import it.pizzafaenza.menu.menu.{Ingredient, SaladCategory}
import it.pizzafaenza.menu.utils.Name

import scala.concurrent.{ExecutionContext, Future}

class SaladCollection(jsonReader: JsonReader)(implicit ec: ExecutionContext):

  private val DBPath = "data/salad.json"

  given Decoder[Salad] = new Decoder[Salad]:
    def apply(c: HCursor): Decoder.Result[Salad] =
      for
        id <- c.downField("id").as[Int]
        name <- c.downField("name").as[String]
        price <- c.downField("price").as[String].map(_.toDouble)
        category <- Right(SaladCategory.Salad)
        ingredients <-
          for
            itaStr <- c.downField("toppings_italian").as[String]
            engStr <- c.downField("toppings_english").as[String]
          yield
            val listIta = itaStr.split(",").toList.map(_.trim)
            val listEng = engStr.split(",").toList.map(_.trim)
            listIta.zip(listEng).map { case (it, en) =>
              Ingredient(Name(it, en))
            }
        allergens <- c.downField("allergens").as[String].map(
          _.split(",").toList.map(Allergen(0, _))
        )
      yield Salad(id, name, category, ingredients, allergens, price)

  def getSalad: Future[List[Salad]] =
    jsonReader.read(DBPath).map { content =>
      content.as[List[Salad]] match
        case Right(l) => l.sortBy(_.id)
        case Left(error) =>
          throw new Exception(s"Failed to decode Salad: $error")
    }
