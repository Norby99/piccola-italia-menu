package it.pizzafaenza.menu.salads

import it.pizzafaenza.menu.mock.{MockInsalatonaReader, MockIngredientsReader}
import it.pizzafaenza.menu.salads.SaladCollection
import it.pizzafaenza.menu.menu.SaladCategory
import it.pizzafaenza.menu.ingredients.IngredientCollection

import scala.concurrent.Future
import org.scalatest.flatspec.AsyncFlatSpec
import org.scalatest.matchers.should.Matchers

class SaladCollectionTest extends AsyncFlatSpec with Matchers:
  "SaladCollection" should "load Insalatona with ingredients from a JSON" in:
    val collection = new SaladCollection(MockInsalatonaReader)

    for
      ingredients <- IngredientCollection(MockIngredientsReader).getIngredients
      salads <- collection.getSalad(ingredients)

      insalata = ingredients.find(_.id == 69).get
      oliveNere = ingredients.find(_.id == 32).get
      pomodoriCalabresi = ingredients.find(_.id == 56).get
      pomodorini = ingredients.find(_.id == 15).get
      radicchio = ingredients.find(_.id == 14).get
      rucola = ingredients.find(_.id == 30).get
      tonno = ingredients.find(_.id == 39).get
    yield
      salads.length shouldBe 1
      val salad = salads.head
      salad.name shouldBe "Insalatona"
      salad.category shouldBe SaladCategory.Salad
      salad.ingredients should contain allOf (insalata, oliveNere, pomodoriCalabresi, pomodorini, radicchio, rucola, tonno)
      salad.price shouldBe 9.0
      succeed
