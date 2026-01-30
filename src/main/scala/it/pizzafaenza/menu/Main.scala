package it.pizzafaenza.menu

import it.pizzafaenza.menu.ingredients.{Ingredient, IngredientCollection}
import it.pizzafaenza.menu.pizza.{Pizza, PizzeCollection}
import it.pizzafaenza.menu.menu.{Menu, MenuDish, MenuItem}
import it.pizzafaenza.menu.json.BrowserJsonReader
import com.raquo.laminar.api.L.*
import it.pizzafaenza.menu.extraToppings.{ExtraTopping, ExtraToppingsCollection}
import it.pizzafaenza.menu.salads.{Salad, SaladCollection}
import org.scalajs.dom
import org.scalajs.dom.window

import scala.concurrent.Future
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue

def ingredientsFuture: Future[List[Ingredient]] =
  IngredientCollection(BrowserJsonReader).getIngredients

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

  val menu1 = Menu.menu1(dishesVar)
  val menu2 = Menu.menu2(dishesVar)

  val app = menu2

  renderOnDomContentLoaded(dom.document.getElementById("app"), app)
