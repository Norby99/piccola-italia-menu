package pizze

import json.JsonReader
import io.circe._
import io.circe.parser._

class PizzeCollection(jsonReader: JsonReader):
  private val pizzeDBPath = "src/main/resources/pizze.json"
  
  val content = jsonReader.read(pizzeDBPath)
  
  def getPizze(): List[Pizza] = ???
  
