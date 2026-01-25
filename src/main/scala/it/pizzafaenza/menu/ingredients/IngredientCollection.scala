package it.pizzafaenza.menu.ingredients

import it.pizzafaenza.menu.json.JsonReader
import io.circe.*
import scala.concurrent.{ExecutionContext, Future}

class IngredientCollection(jsonReader: JsonReader)(implicit
    ec: ExecutionContext
):
  private val DBPath = "data/ingredienti.json"

  given Decoder[Ingredient] = new Decoder[Ingredient]:
    final def apply(c: HCursor): Decoder.Result[Ingredient] =
      for
        id <- c.downField("id_ingrediente").as[Int]
        nameItalian <- c.downField("nome_italiano").as[String]
        nameEnglish <- c.downField("nome_inglese").as[String]
        allergen <- c.downField("tipo").as[String]
      yield Ingredient(id, Name(nameItalian, nameEnglish), Allergen(allergen))

  def getIngredients: Future[List[Ingredient]] =
    jsonReader.read(DBPath).map { content =>
      content.as[List[Ingredient]] match
        case Right(l) => l.sortBy(_.id)
        case Left(error) =>
          throw new Exception(s"Failed to decode Ingredient: $error")
    }
