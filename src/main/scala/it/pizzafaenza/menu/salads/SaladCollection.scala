package it.pizzafaenza.menu.salads

import it.pizzafaenza.menu.json.JsonReader
import io.circe.*
import it.pizzafaenza.menu.ingredients.Ingredient
import it.pizzafaenza.menu.menu.{MenuDishCollection, SaladCategory}

import scala.concurrent.{ExecutionContext, Future}

class SaladCollection(jsonReader: JsonReader)(implicit ec: ExecutionContext)
    extends MenuDishCollection[Salad](
      jsonReader,
      "data/insalate.json",
      SaladCollection.saladDecoder
    ):

  def getSalad(ingredients: List[Ingredient]): Future[List[Salad]] =
    getDish(ingredients)

object SaladCollection:
  private def saladDecoder(ingredients: List[Ingredient]): Decoder[Salad] =
    new Decoder[Salad]:
      def apply(c: HCursor): Decoder.Result[Salad] =
        for
          name <- c.downField("nomeInsalata").as[String]
          category <- Right(SaladCategory.Salad)
          ingr <-
            val ingIds: List[Int] =
              c.downField("ingredienti").as[String].map(
                _.split(",").map(_.trim.toInt).toList
              ).getOrElse(List.empty)
            ingredients.filter(i => ingIds.contains(i.id)) match
              case l if l.size == ingIds.size => Right(l)
              case _ =>
                Left(
                  DecodingFailure(
                    s"At least an ingredient not found for salad $name with ingredients IDs: ${ingIds.mkString(", ")}",
                    c.history
                  )
                )
          price <- c.downField("prezzo").as[String].map(_.toDouble)
        yield Salad(name, category, ingr, price)
