package it.pizzafaenza.menu.pizze

import it.pizzafaenza.menu.json.JsonReader
import io.circe.*
import it.pizzafaenza.menu.Ingredienti.Ingredient

import scala.concurrent.{ExecutionContext, Future}

class PizzeCollection(jsonReader: JsonReader)(implicit ec: ExecutionContext):
  private val DBPath = "data/pizze.json"

  private def pizzaDecoder(ingredients: List[Ingredient]): Decoder[Pizza] =
    new Decoder[Pizza]:
      def apply(c: HCursor): Decoder.Result[Pizza] =
        for
          name <- c.downField("nomePizza").as[String]
          category <-
            c.downField("nome_tipo").as[String].flatMap { typeStr =>
              PizzaCategory.values.find(_.description == typeStr)
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

  def getPizze(ingredients: List[Ingredient]): Future[List[Pizza]] =
    jsonReader.read(DBPath).map { content =>
      given Decoder[Pizza] = pizzaDecoder(ingredients)

      println("Deconding...")
      io.circe.parser.parse(content.noSpaces) match
        case Right(json) =>
          val res = json.asArray match
            case Some(pizzeArray) =>
              pizzeArray.flatMap { pizzaJson =>
                pizzaJson.as[Pizza] match
                  case Right(pizza) => Some(pizza)
                  case Left(error) =>
                    println(s"Skipping pizza: ${error.getMessage}")
                    None
              }.toList
            case None =>
              throw new Exception("JSON is not an array")
          println("Decoded pizze: " + res.size)
          res
        case Left(error) =>
          throw new Exception(s"Failed to parse JSON: $error")
    }
