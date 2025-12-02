package it.pizzafaenza.menu.pizze

import io.circe.*
import it.pizzafaenza.menu.json.JsonReader

import scala.concurrent.{ExecutionContext, Future}

class PizzeCollection(jsonReader: JsonReader)(implicit ec: ExecutionContext):
  private val pizzeDBPath = "pizze.json"

  given Decoder[Pizza] = new Decoder[Pizza]:
    final def apply(c: HCursor): Decoder.Result[Pizza] =
      for
        name <- c.downField("nomePizza").as[String]
        pizzaType <- c.downField("nome_tipo").as[String]
        ingredients <-
          c.downField("ingredienti").as[String].map(_.split(",").toList)
        price <- c.downField("prezzo").as[String].map(_.toDouble)
      yield Pizza(name, pizzaType, ingredients, price)

  def getPizze: Future[List[Pizza]] =
    jsonReader.read(pizzeDBPath).map { content =>
      content.as[List[Pizza]] match
        case Right(lista) => lista
        case Left(error) =>
          throw new Exception(s"Failed to decode Pizze: $error")
    }
