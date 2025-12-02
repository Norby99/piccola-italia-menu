package it.pizzafaenza.menu.json

import io.circe.*
import scala.concurrent.Future

trait JsonReader:
  def read(path: String): Future[Json]
