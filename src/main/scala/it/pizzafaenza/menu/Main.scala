package it.pizzafaenza.menu

import it.pizzafaenza.menu.pizza.PizzaCollection
import it.pizzafaenza.menu.menu.{Menu, MenuDish}
import it.pizzafaenza.menu.json.BrowserJsonReader
import it.pizzafaenza.menu.extraToppings.{ExtraTopping, ExtraToppingsCollection}
import it.pizzafaenza.menu.allergens.{Allergen, AllergenCollection}
import it.pizzafaenza.menu.salads.SaladCollection

import com.raquo.laminar.api.L.*
import org.scalajs.dom
import org.scalajs.dom.{URLSearchParams, window}

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
  val extraToppingCollection = Var(List.empty[ExtraTopping])
  val allergensCollection = Var(List.empty[Allergen])

  def reloadAll(): Future[Unit] =
    for
      pizzas <- PizzaCollection(BrowserJsonReader).getPizza
      salads <- SaladCollection(BrowserJsonReader).getSalad
      extraToppings <-
        ExtraToppingsCollection(BrowserJsonReader).getExtraTopping
      allergens <- AllergenCollection(BrowserJsonReader).getAllergensToShow
    yield
      dishesVar.set(pizzas ++ salads)
      extraToppingCollection.set(extraToppings)
      allergensCollection.set(allergens)

  reloadAll()

  val menuGetter = new URLSearchParams(window.location.search).get("menu")

  val app = menuGetter match
    case "2" =>
      Menu.menu2(dishesVar, extraToppingCollection, allergensCollection)
    case _ => Menu.menu1(dishesVar)

  renderOnDomContentLoaded(
    dom.document.getElementById("app"),
    div(
      // Polling the reloading of the menu from files every 1 min
      EventStream.periodic(60 * 1000)
        .flatMapSwitch(_ => EventStream.fromFuture(reloadAll()))
        --> Observer.empty,
      app
    )
  )
