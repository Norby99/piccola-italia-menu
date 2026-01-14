package it.pizzafaenza.menu

import com.raquo.laminar.api.L.*
import org.scalajs.dom
import org.scalajs.dom.window
import it.pizzafaenza.menu.Ingredienti.IngredientCollection
import it.pizzafaenza.menu.pizze.{Pizza, PizzeCollection}
import it.pizzafaenza.menu.pizze.PizzaCategory.*
import it.pizzafaenza.menu.json.BrowserJsonReader

import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue
import it.pizzafaenza.menu.UIElements.{CategoryCellRenderer, PizzaCellRenderer}

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

  val menu1 = pizzeVar.signal.map { pizze =>
    val orderMap = Map(
      Classiche -> 1
      /*Bianche -> 2,
      Conditissime -> 3,
      Stese -> 4*/
    )

    pizze
      .filter(p => orderMap.contains(p.category))
      .groupBy(_.category)
      .toSeq
      .sortBy { case (category, _) =>
        orderMap.getOrElse(category, Int.MaxValue)
      }
  }

  val app = div(
    cls := "pizze",
    children <-- menu1.map { grouped =>
      grouped.flatMap { case (category, pizze) =>
        CategoryCellRenderer(category).render +: pizze.map { pizza =>
          PizzaCellRenderer(pizza).render
        }
      }
    }
  )

  renderOnDomContentLoaded(dom.document.getElementById("app"), app)
