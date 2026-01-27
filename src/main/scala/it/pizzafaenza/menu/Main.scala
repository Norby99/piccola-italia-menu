package it.pizzafaenza.menu

import it.pizzafaenza.menu.ingredients.{Ingredient, IngredientCollection}
import it.pizzafaenza.menu.pizza.{Pizza, PizzeCollection}
import it.pizzafaenza.menu.menu.Menu
import it.pizzafaenza.menu.json.BrowserJsonReader
import com.raquo.laminar.api.L.*
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

@main def runApp(): Unit =
  val windowWidth = Var(window.outerWidth)

  window.addEventListener(
    "resize",
    { (_: dom.Event) =>
      windowWidth.set(window.outerWidth)
    }
  )

  val pizzeVar = Var(List.empty[Pizza])
  pizzeFuture.foreach { pizze =>
    pizzeVar.set(pizze)
  }

  val menu1 = Menu.menu1(pizzeVar)
  val menu2 = Menu.menu2(pizzeVar)

  val app = menu2

  renderOnDomContentLoaded(dom.document.getElementById("app"), app)
