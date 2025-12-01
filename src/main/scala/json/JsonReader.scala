package json

import io.circe.*
import io.circe.parser.*

import scala.util.Using

trait JsonReader:
  def read(path: String): Json

object FileJsonReader extends JsonReader:
  override def read(path: String): Json =
    val result = Using(scala.io.Source.fromFile(path)) { source =>
      parse(source.mkString)
    }

    result match
      case scala.util.Success(Right(json)) => json
      case scala.util.Success(Left(error)) =>
        throw new Exception(s"Failed to parse JSON: $error")
      case scala.util.Failure(exception) => throw exception
