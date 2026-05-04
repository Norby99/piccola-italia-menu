package it.pizzafaenza.menu.pizza

import it.pizzafaenza.menu.ingredients.IngredientCollection

import scala.concurrent.Future
import org.scalatest.flatspec.AsyncFlatSpec
import org.scalatest.matchers.should.Matchers
import it.pizzafaenza.menu.mock.{
  MockMargheritaReader,
  MockIngredientsReader,
  MockAllergensReader
}
import it.pizzafaenza.menu.allergens.AllergenCollection

class PizzeCollectionTest extends AsyncFlatSpec with Matchers:

  "PizzeCollection" should "load margherita with ingredients from a JSON" in:
    val collection = new PizzeCollection(MockMargheritaReader)

    for
      allergens <- AllergenCollection(MockAllergensReader).getAllergens
      ingredients <-
        IngredientCollection(MockIngredientsReader).getIngredients(allergens)
      pizze <- collection.getPizze(ingredients)

      salsaPomodoro = ingredients.find(_.id == 1).get
      mozzarella = ingredients.find(_.id == 2).get
    yield
      pizze.length shouldBe 1
      val pizza = pizze.head
      pizza.name shouldBe "Margherita"
      pizza.ingredients should contain allOf (salsaPomodoro, mozzarella)
      pizza.price shouldBe 4.0
      succeed
