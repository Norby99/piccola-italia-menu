package it.pizzafaenza.menu

import it.pizzafaenza.menu.Ingredienti.IngredientCollection
import it.pizzafaenza.menu.pizze.{Pizza, PizzeCollection}
import it.pizzafaenza.menu.pizze.PizzaCategory.*
import it.pizzafaenza.menu.menu.Menu
import it.pizzafaenza.menu.json.BrowserJsonReader

import it.pizzafaenza.menu.UIElements.{CategoryCellRenderer, PizzaCellRenderer}

import com.raquo.laminar.api.L.*
import org.scalajs.dom
import org.scalajs.dom.window
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue

@main def runApp(): Unit =
  val windowWidth = Var(window.outerWidth)

  window.addEventListener(
    "resize",
    { (_: dom.Event) =>
      windowWidth.set(window.outerWidth)
    }
  )

  val ingredientsFuture = IngredientCollection(BrowserJsonReader).getIngredients

  val pizzeFuture = ingredientsFuture.flatMap { ing =>
    PizzeCollection(BrowserJsonReader).getPizze(ing)
  }

  val pizzeVar = Var(List.empty[Pizza])

  pizzeFuture.foreach { pizze =>
    pizzeVar.set(pizze)
  }

  val menu1 = Menu.menu1(pizzeVar)
  val menu2 = Menu.menu2(pizzeVar)

  val app = menu2

  renderOnDomContentLoaded(dom.document.getElementById("app"), app)
