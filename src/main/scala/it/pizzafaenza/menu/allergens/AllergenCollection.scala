package it.pizzafaenza.menu.allergens

import io.circe.{Decoder, HCursor}
import it.pizzafaenza.menu.json.JsonReader

import scala.concurrent.{ExecutionContext, Future}

class AllergenCollection(jsonReader: JsonReader)(implicit
    ec: ExecutionContext
):
  private val DBPath = "data/allergeni.json"

  given Decoder[Allergen] = new Decoder[Allergen]:
    final def apply(c: HCursor): Decoder.Result[Allergen] =
      for
        id <- c.downField("id").as[Int]
        name <- c.downField("tipo").as[String]
      yield Allergen(id, name)

  def getAllergens: Future[List[Allergen]] =
    jsonReader.read(DBPath).map { content =>
      content.as[List[Allergen]] match
        case Right(l) => l.sortBy(_.id)
        case Left(error) =>
          throw new Exception(s"Failed to decode Allergen: $error")
    }

  /** Returns a list of allergens without 'Null', sorted by their ID. */
  def getAllergensToShow: Future[List[Allergen]] =
    getAllergens.map(_.filterNot(_.name.equalsIgnoreCase("Null")))
