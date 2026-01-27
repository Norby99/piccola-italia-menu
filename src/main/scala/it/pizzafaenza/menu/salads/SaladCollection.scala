package it.pizzafaenza.menu.salads

import it.pizzafaenza.menu.json.JsonReader
import io.circe.*
import it.pizzafaenza.menu.ingredients.Ingredient

import scala.concurrent.{ExecutionContext, Future}

class SaladCollection(jsonReader: JsonReader)(implicit ec: ExecutionContext):
  private val DBPath = "data/insalate.json"

  private def saladDecoder(ingredients: List[Ingredient]): Decoder[Salad] =
    new Decoder[Salad]:
      def apply(c: HCursor): Decoder.Result[Salad] =
        for
          name <- c.downField("nomeInsalata").as[String]
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
        yield Salad(name, ingr, price)

  def getSalad(ingredients: List[Ingredient]): Future[List[Salad]] =
    jsonReader.read(DBPath).map { content =>
      given Decoder[Salad] = saladDecoder(ingredients)

      println("Decoding...")
      io.circe.parser.parse(content.noSpaces) match
        case Right(json) =>
          val res = json.asArray match
            case Some(saladArray) =>
              saladArray.flatMap { saladJson =>
                saladJson.as[Salad] match
                  case Right(salad) =>
                    println("---:" + salad)
                    Some(salad)
                  case Left(error) =>
                    println(s"Skipping salad: ${error.getMessage}")
                    None
              }.toList
            case None =>
              throw new Exception("JSON is not an array")
          println("Decoded salad: " + res.size)
          res
        case Left(error) =>
          throw new Exception(s"Failed to parse JSON: $error")
    }
