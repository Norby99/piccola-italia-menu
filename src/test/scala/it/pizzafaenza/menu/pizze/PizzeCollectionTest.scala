package it.pizzafaenza.menu.pizze

import scala.concurrent.Future

import org.scalatest.flatspec.AsyncFlatSpec
import org.scalatest.matchers.should.Matchers

import it.pizzafaenza.menu.mock.MockMargheritaReader
import it.pizzafaenza.menu.pizze.{Pizza, PizzeCollection}

class PizzeCollectionTest extends AsyncFlatSpec with Matchers:

  "PizzeCollection" should "load margherita w/o ingredients from a JSON" in:
    val collection = new PizzeCollection(MockMargheritaReader)
    val pizze: Future[List[Pizza]] = collection.getPizze

    pizze.map { p =>
      p.length shouldBe 1
      p.head.name shouldBe "Margherita"
      p.head.price shouldBe 4.0
      succeed
    }
