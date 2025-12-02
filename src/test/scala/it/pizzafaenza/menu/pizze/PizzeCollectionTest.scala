package it.pizzafaenza.menu.pizze

import it.pizzafaenza.menu.pizze.{Pizza, PizzeCollection}
import it.pizzafaenza.menu.utils.MockJsonReader

import org.scalatest.flatspec.AsyncFlatSpec
import org.scalatest.matchers.should.Matchers

import scala.concurrent.Future

class PizzeCollectionTest extends AsyncFlatSpec with Matchers:
  "PizzeCollection" should "load pizze from JSON" in:
    val pizzeCollection = new PizzeCollection(MockJsonReader)
    val pizzeFuture: Future[List[Pizza]] = pizzeCollection.getPizze

    pizzeFuture.map { pizze =>
      println(pizze)
      pizze should not be empty
      succeed
    }
