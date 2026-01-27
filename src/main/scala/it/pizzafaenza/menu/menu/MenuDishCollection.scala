package it.pizzafaenza.menu.menu

import io.circe.*
import it.pizzafaenza.menu.ingredients.Ingredient
import it.pizzafaenza.menu.json.JsonReader
import it.pizzafaenza.menu.menu.MenuDish

import scala.concurrent.{ExecutionContext, Future}

class MenuDishCollection[T <: MenuDish](
    jsonReader: JsonReader,
    dbPath: String,
    createDecoder: List[Ingredient] => Decoder[T]
)(implicit ec: ExecutionContext):

  def getDish(ingredients: List[Ingredient]): Future[List[T]] =
    jsonReader.read(dbPath).map { content =>
      given Decoder[T] = createDecoder(ingredients)

      println("Decoding...")
      io.circe.parser.parse(content.noSpaces) match
        case Right(json) =>
          val res = json.asArray match
            case Some(itemArray) =>
              itemArray.flatMap { itemJson =>
                itemJson.as[T] match
                  case Right(item) => Some(item)
                  case Left(error) =>
                    println(s"Skipping item: ${error.getMessage}")
                    None
              }.toList
            case None =>
              throw new Exception("JSON is not an array")
          println(s"Decoded items: ${res.size}")
          res
        case Left(error) =>
          throw new Exception(s"Failed to parse JSON: $error")
    }
