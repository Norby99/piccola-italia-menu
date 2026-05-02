package it.pizzafaenza.menu.allergens

import it.pizzafaenza.menu.mock.MockAllergensReader
import org.scalatest.flatspec.AsyncFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.concurrent.Future

class AllergenCollectionTest extends AsyncFlatSpec with Matchers:
  "AllergenCollection" should "load uova from a JSON" in:
    val collection = new AllergenCollection(MockAllergensReader)

    for
      allergens <- collection.getAllergens
      uova = allergens.find(_.id == 2).get
    yield
      uova.id shouldBe 2
      uova.name shouldBe "Uova"
      succeed
