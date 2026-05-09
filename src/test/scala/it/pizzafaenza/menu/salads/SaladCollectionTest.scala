package it.pizzafaenza.menu.salads

import it.pizzafaenza.menu.mock.MockInsalatonaReader
import it.pizzafaenza.menu.menu.SaladCategory

import scala.concurrent.Future
import org.scalatest.flatspec.AsyncFlatSpec
import org.scalatest.matchers.should.Matchers

class SaladCollectionTest extends AsyncFlatSpec with Matchers:

  "SaladCollection" should "load Insalatona with ingredients from a JSON" in:
    val collection = new SaladCollection(MockInsalatonaReader)

    for
      salads <- collection.getSalad
    yield
      salads.length shouldBe 1
      val salad = salads.head
      salad.name shouldBe "Insalatona"
      salad.category shouldBe SaladCategory.Salad
      salad.ingredients.italian should contain allOf ("insalata", "olive nere", "pomodori calabresi", "pomodorini", "radicchio", "rucola", "tonno")
      salad.price shouldBe 9.0
      succeed
