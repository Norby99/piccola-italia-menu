package it.pizzafaenza.menu

import it.pizzafaenza.menu.ingredients.{Ingredient, IngredientCollection}
import it.pizzafaenza.menu.pizza.{Pizza, PizzeCollection}
import it.pizzafaenza.menu.menu.{Menu, MenuDish}
import it.pizzafaenza.menu.json.BrowserJsonReader
import com.raquo.laminar.api.L.*
import it.pizzafaenza.menu.extraToppings.{ExtraTopping, ExtraToppingsCollection}
import it.pizzafaenza.menu.allergens.{Allergen, AllergenCollection}
import it.pizzafaenza.menu.salads.{Salad, SaladCollection}
import org.scalajs.dom
import org.scalajs.dom.window

import scala.concurrent.Future
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue

def allergensFuture: Future[List[Allergen]] =
  AllergenCollection(BrowserJsonReader).getAllergens

def ingredientsFuture: Future[List[Ingredient]] =
  allergensFuture.flatMap { allergens =>
    IngredientCollection(BrowserJsonReader).getIngredients(allergens)
  }

def pizzeFuture: Future[List[Pizza]] =
  ingredientsFuture.flatMap { ing =>
    PizzeCollection(BrowserJsonReader).getPizze(ing)
  }

def saladFuture: Future[List[Salad]] =
  ingredientsFuture.flatMap { ing =>
    SaladCollection(BrowserJsonReader).getSalad(ing)
  }

def extraToppingFuture: Future[List[ExtraTopping]] =
  ExtraToppingsCollection(BrowserJsonReader).getExtraTopping

def allergenFutureToShow: Future[List[Allergen]] =
  AllergenCollection(BrowserJsonReader).getAllergensToShow

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
    pizze <- pizzeFuture
    insalate <- saladFuture
  yield dishesVar.set(pizze ++ insalate)

  val extraToppingCollection = Var(List.empty[ExtraTopping])
  for
    extraToppings <- extraToppingFuture
  yield extraToppingCollection.set(extraToppings)

  val allergensCollection = Var(List.empty[Allergen])
  for
    allergens <- allergenFutureToShow
  yield allergensCollection.set(allergens)

  val menu1 = Menu.menu1(dishesVar)
  val menu2 = Menu.menu2(dishesVar, extraToppingCollection, allergensCollection)

  val app = menu2

  renderOnDomContentLoaded(dom.document.getElementById("app"), app)
