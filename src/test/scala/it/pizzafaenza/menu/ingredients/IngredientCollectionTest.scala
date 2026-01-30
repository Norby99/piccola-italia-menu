package it.pizzafaenza.menu.ingredients

import it.pizzafaenza.menu.ingredients.{Ingredient, IngredientCollection}
import it.pizzafaenza.menu.mock.MockIngredientsReader
import org.scalatest.flatspec.AsyncFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.concurrent.Future

class IngredientCollectionTest extends AsyncFlatSpec with Matchers:

  "IngredientCollection" should "load gamberetti from a JSON" in:
    val collection = new IngredientCollection(MockIngredientsReader)

    for
      ingredients <- collection.getIngredients
      gamberetti = ingredients.find(_.id == 50).get
    yield
      gamberetti.id shouldBe 50
      gamberetti.name.italian shouldBe "gamberetti"
      gamberetti.name.english shouldBe "shrimps"
      gamberetti.allergen.name shouldBe "Pesce"
      succeed
