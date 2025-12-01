package pizze

import json.FileJsonReader
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers

class PizzeCollectionTest extends AnyFlatSpec with Matchers:
  "Generic test" should "pass" in:
    val pizze = PizzeCollection(FileJsonReader)

    println(pizze.content)
