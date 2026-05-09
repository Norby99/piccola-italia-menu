package it.pizzafaenza.menu

import it.pizzafaenza.menu.pizza.PizzaCollection
import it.pizzafaenza.menu.menu.{Menu, MenuDish}
import it.pizzafaenza.menu.json.BrowserJsonReader
import com.raquo.laminar.api.L.*
import it.pizzafaenza.menu.extraToppings.{ExtraTopping, ExtraToppingsCollection}
import it.pizzafaenza.menu.allergens.{Allergen, AllergenCollection}
import it.pizzafaenza.menu.salads.SaladCollection
import org.scalajs.dom
import org.scalajs.dom.window

import scala.concurrent.Future
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue

@main def runApp(): Unit =
  val windowWidth = Var(window.outerWidth)

  window.addEventListener(
    "resize",
    { (_: dom.Event) =>
      windowWidth.set(window.outerWidth)
    }
  )

  val dishesVar = Var(List.empty[MenuDish])
  for
    pizzas <- PizzaCollection(BrowserJsonReader).getPizza
    salads <- SaladCollection(BrowserJsonReader).getSalad
  yield dishesVar.set(pizzas ++ salads)

  val extraToppingCollection = Var(List.empty[ExtraTopping])
  for
    extraToppings <- ExtraToppingsCollection(BrowserJsonReader).getExtraTopping
  yield extraToppingCollection.set(extraToppings)

  val allergensCollection = Var(List.empty[Allergen])
  for
    allergens <- AllergenCollection(BrowserJsonReader).getAllergensToShow
  yield allergensCollection.set(allergens)

  val menu1 = Menu.menu1(dishesVar)
  val menu2 = Menu.menu2(dishesVar, extraToppingCollection, allergensCollection)

  val app = menu2

  renderOnDomContentLoaded(dom.document.getElementById("app"), app)
