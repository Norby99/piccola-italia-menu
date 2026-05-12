package it.pizzafaenza.menu.pizza

import scala.concurrent.Future
import org.scalatest.flatspec.AsyncFlatSpec
import org.scalatest.matchers.should.Matchers
import it.pizzafaenza.menu.mock.{MockMargheritaReader, MockAllergensReader}

class PizzaCollectionTest extends AsyncFlatSpec with Matchers:

  "PizzaCollection" should "load margherita with ingredients from a JSON" in:
    val collection = new PizzaCollection(MockMargheritaReader)

    for
      pizzas <- collection.getPizza
    yield
      pizzas.length shouldBe 1
      val pizza = pizzas.head
      pizza.name shouldBe "Margherita"
      pizza.ingredients.map(
        _.name.italian
      ) should contain allOf ("salsa di pomodoro", "mozzarella")
      pizza.price shouldBe 4.5
      succeed
