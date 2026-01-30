package it.pizzafaenza.menu.extraToppings

import it.pizzafaenza.menu.menu.ExtraToppingCategory
import it.pizzafaenza.menu.mock.MockExtraToppingsReader
import org.scalatest.flatspec.AsyncFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.concurrent.Future

class ExtraToppingsCollectionTest extends AsyncFlatSpec with Matchers:
  "Mozzarella di Bufala" should "be loaded from a JSON" in:
    val collection = new ExtraToppingsCollection(MockExtraToppingsReader)

    for
      extraToppings <- collection.getExtraTopping
      bufala = extraToppings.find(_.id == 2).get
    yield
      bufala.id shouldBe 2
      bufala.name.italian shouldBe "Mozzarella di Bufala"
      bufala.name.english shouldBe "Buffalo mozzarella"
      bufala.category shouldBe ExtraToppingCategory.ExtraTopping
      bufala.price shouldBe 2.5
      succeed
