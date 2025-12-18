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

  val ingredientsFuture = IngredientCollection(BrowserJsonReader).getIngredients

  val pizzeFuture = ingredientsFuture.flatMap { ing =>
    PizzeCollection(BrowserJsonReader).getPizze(ing)
  }

  val pizzeVar = Var(List.empty[Pizza])

  pizzeFuture.foreach { pizze =>
    pizzeVar.set(pizze)
  }

  val pizzeMenu1 = pizzeVar.signal.map { pizze =>
    val orderMap = Map(
      "Pizze classiche" -> 1,
      "Pizze bianche" -> 2,
      "Pizze conditissime" -> 3
    )

    pizze
      .filter(p =>
        p.pizzaType == "Pizze classiche" || p.pizzaType == "Pizze bianche" || p.pizzaType == "Pizze conditissime"
      )
      .sortBy(p => orderMap.getOrElse(p.pizzaType, Int.MaxValue))
  }

  val app = div(
    cls := "pizze",
    children <-- pizzeMenu1.signal.map { pizze =>
      pizze.map { pizza =>
        PizzaCellRenderer(pizza).render
      }
    }
  )

  renderOnDomContentLoaded(dom.document.getElementById("app"), app)
