package it.pizzafaenza.menu.pizza

import it.pizzafaenza.menu.json.JsonReader
import io.circe.*
import it.pizzafaenza.menu.ingredients.Ingredient
import it.pizzafaenza.menu.menu.MenuDishCollection

import scala.concurrent.{ExecutionContext, Future}

class PizzeCollection(jsonReader: JsonReader)(implicit ec: ExecutionContext)
    extends MenuDishCollection[Pizza](
      jsonReader,
      "data/pizze.json",
      PizzeCollection.pizzaDecoder
    ):

  def getPizze(ingredients: List[Ingredient]): Future[List[Pizza]] =
    getDish(ingredients)

object PizzeCollection:
  private def pizzaDecoder(ingredients: List[Ingredient]): Decoder[Pizza] =
    new Decoder[Pizza]:
      def apply(c: HCursor): Decoder.Result[Pizza] =
        for
          name <- c.downField("nomePizza").as[String]
          category <-
            c.downField("nome_tipo").as[String].flatMap { typeStr =>
              PizzaCategory.values.find(_.title == typeStr)
                .toRight(DecodingFailure(
                  s"Pizza category not found: $typeStr",
                  c.history
                ))
            }
          ingr <-
            val ingIds: List[Int] =
              c.downField("ingredienti").as[String].map(
                _.split(",").map(_.trim.toInt).toList
              ).getOrElse(List.empty)
            ingredients.filter(i => ingIds.contains(i.id)) match
              case l if l.nonEmpty => Right(l)
              case _ =>
                Left(
                  DecodingFailure(
                    s"Ingredients with IDs ${ingIds.mkString(", ")} not found",
                    c.history
                  )
                )
          price <- c.downField("prezzo").as[String].map(_.toDouble)
        yield Pizza(name, category, ingr, price)
