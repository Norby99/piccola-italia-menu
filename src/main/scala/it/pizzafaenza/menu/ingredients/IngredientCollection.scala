package it.pizzafaenza.menu.ingredients

import it.pizzafaenza.menu.json.JsonReader
import io.circe.*
import it.pizzafaenza.menu.utils.Name
import it.pizzafaenza.menu.allergens.Allergen

import scala.concurrent.{ExecutionContext, Future}

class IngredientCollection(jsonReader: JsonReader)(implicit
    ec: ExecutionContext
):
  private val DBPath = "data/ingredienti.json"

  def getIngredients(allergens: List[Allergen]): Future[List[Ingredient]] =
    given Decoder[Ingredient] = new Decoder[Ingredient]:
      final def apply(c: HCursor): Decoder.Result[Ingredient] =
        for
          id <- c.downField("id_ingrediente").as[Int]
          nameItalian <- c.downField("nome_italiano").as[String]
          nameEnglish <- c.downField("nome_inglese").as[String]
          allergenId <- c.downField("id").as[Int]

          allergen <- allergens.find(_.id == allergenId)
            .toRight(DecodingFailure(
              s"Allergene con id $allergenId non trovato",
              c.history
            ))
        yield Ingredient(id, Name(nameItalian, nameEnglish), allergen)

    jsonReader.read(DBPath).map { content =>
      content.as[List[Ingredient]] match
        case Right(l) => l.sortBy(_.id)
        case Left(error) =>
          throw new Exception(s"Failed to decode Ingredient: $error")
    }
