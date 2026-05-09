package it.pizzafaenza.menu.pizza

import it.pizzafaenza.menu.json.JsonReader
import io.circe.*
import it.pizzafaenza.menu.allergens.Allergen
import it.pizzafaenza.menu.menu.{Ingredients, PizzaCategory}

import scala.concurrent.{ExecutionContext, Future}

class PizzaCollection(jsonReader: JsonReader)(implicit ec: ExecutionContext):

  private val DBPath = "data/pizza.json"

  given Decoder[Pizza] = new Decoder[Pizza]:
    def apply(c: HCursor): Decoder.Result[Pizza] =
      for
        id <- c.downField("id").as[Int]
        name <- c.downField("name").as[String]
        category <-
          c.downField("category").as[String].flatMap { typeStr =>
            PizzaCategory.values.find(_.title == typeStr)
              .toRight(DecodingFailure(
                s"Pizza category not found: $typeStr",
                c.history
              ))
          }
        dough <- c.downField("dough").as[String]
        price <- c.downField("price").as[String].map(_.toDouble)
        ingredients <-
          for
            ingr_ita <- c.downField("toppings_italian").as[String].map(_.split(
              ","
            ).toList)
            ingr_eng <- c.downField("toppings_english").as[String].map(_.split(
              ","
            ).toList)
          yield Ingredients(ingr_ita, ingr_eng)
        allergens <- c.downField("allergens").as[String].map(
          _.split(",").toList.map(Allergen(0, _))
        )
      yield Pizza(id, name, category, dough, ingredients, allergens, price)

  def getPizza: Future[List[Pizza]] =
    jsonReader.read(DBPath).map { content =>
      content.as[List[Pizza]] match
        case Right(l) => l.sortBy(_.id)
        case Left(error) =>
          throw new Exception(s"Failed to decode Pizza: $error")
    }
