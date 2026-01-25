package it.pizzafaenza.menu.ingredients

import it.pizzafaenza.menu.ingredients.{Ingredient, IngredientCollection}
import it.pizzafaenza.menu.mock.MockGamberettiReader
import org.scalatest.flatspec.AsyncFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.concurrent.Future

class IngredientCollectionTest extends AsyncFlatSpec with Matchers:

  "IngredientCollection" should "load gamberetti from a JSON" in:
    val collection = new IngredientCollection(MockGamberettiReader)
    val ingredients: Future[List[Ingredient]] = collection.getIngredients

    ingredients.map { i =>
      i.length shouldBe 1
      i.head.id shouldBe 50
      i.head.name.italian shouldBe "gamberetti"
      i.head.name.english shouldBe "shrimps"
      i.head.allergen.name shouldBe "Pesce"
      succeed
    }
