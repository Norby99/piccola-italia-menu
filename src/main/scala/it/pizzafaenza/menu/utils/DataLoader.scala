package it.pizzafaenza.menu.utils

import it.pizzafaenza.menu.extraToppings.{ExtraTopping, ExtraToppingsCollection}
import it.pizzafaenza.menu.allergens.{Allergen, AllergenCollection}
import it.pizzafaenza.menu.json.BrowserJsonReader
import it.pizzafaenza.menu.menu.MenuDish
import it.pizzafaenza.menu.pizza.PizzaCollection
import it.pizzafaenza.menu.salads.SaladCollection

import scala.concurrent.Future
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue

object DataLoader:
  def loadAll(): Future[AppData] =
    for
      pizzas <- PizzaCollection(BrowserJsonReader).getPizza
      salads <- SaladCollection(BrowserJsonReader).getSalad
      toppings <- ExtraToppingsCollection(BrowserJsonReader).getExtraTopping
      allergens <- AllergenCollection(BrowserJsonReader).getAllergensToShow
    yield AppData(pizzas ++ salads, toppings, allergens)

case class AppData(
    dishes: List[MenuDish],
    extraToppings: List[ExtraTopping],
    allergens: List[Allergen]
)
