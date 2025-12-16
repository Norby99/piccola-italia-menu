package it.pizzafaenza.menu

import com.raquo.laminar.api.L.*
import org.scalajs.dom
import org.scalajs.dom.window
import it.pizzafaenza.menu.Ingredienti.IngredientCollection
import it.pizzafaenza.menu.pizze.{Pizza, PizzeCollection}
import it.pizzafaenza.menu.json.BrowserJsonReader
import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import it.pizzafaenza.menu.UIElements.PizzaCellRenderer

def text_resizer(n: Float, ratio: Float): Float = n / (2550 / ratio)

@main def runApp(): Unit =
  val windowWidth = Var(window.outerWidth)

  window.addEventListener(
    "resize",
    { (_: dom.Event) =>
      windowWidth.set(window.outerWidth)
    }
  )

  val ingredientiFuture = IngredientCollection(BrowserJsonReader).getIngredients

  val pizzeFuture = ingredientiFuture.flatMap { ingrList =>
    PizzeCollection(BrowserJsonReader).getPizze(ingrList)
  }

  val pizzeVar = Var(List.empty[Pizza])

  pizzeFuture.foreach { pizze =>
    pizzeVar.set(pizze)
  }

  val app = div(
    h1("Menu Pizzeria Faenza"),
    h2("Le nostre pizze"),
    children <-- pizzeVar.signal.map { pizze =>
      pizze.map { pizza =>
        PizzaCellRenderer(pizza).render
      }
    }
  )

  renderOnDomContentLoaded(dom.document.getElementById("app"), app)
