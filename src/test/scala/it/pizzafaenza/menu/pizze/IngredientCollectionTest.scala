package it.pizzafaenza.menu.pizze

import scala.concurrent.Future

import org.scalatest.flatspec.AsyncFlatSpec
import org.scalatest.matchers.should.Matchers

import it.pizzafaenza.menu.mock.MockGamberettiReader
import it.pizzafaenza.menu.Ingredienti.{Ingredient, IngredientCollection}

class IngredientCollectionTest extends AsyncFlatSpec with Matchers:

  "IngredientiCollection" should "load gamberetti from a JSON" in:
    val collection = new IngredientCollection(MockGamberettiReader)
    val ingredienti: Future[List[Ingredient]] = collection.getIngredients

    ingredienti.map { i =>
      i.length shouldBe 1
      i.head.name.italian shouldBe "gamberetti"
      i.head.name.english shouldBe "shrimps"
      succeed
    }
