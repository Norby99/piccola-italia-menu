package it.pizzafaenza.menu.allergens

import it.pizzafaenza.menu.mock.MockAllergensReader
import org.scalatest.flatspec.AsyncFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.concurrent.Future

class AllergenCollectionTest extends AsyncFlatSpec with Matchers:
  "AllergenCollection" should "load eggs from a JSON" in:
    val collection = new AllergenCollection(MockAllergensReader)

    for
      allergens <- collection.getAllergens
      eggs = allergens.find(_.id == 2).get
    yield
      eggs.id shouldBe 2
      eggs.name shouldBe "Uova"
      succeed
