package it.pizzafaenza.menu.utils

import com.raquo.laminar.api.L.*
import it.pizzafaenza.menu.menu.Menu
import org.scalajs.dom.{document, window}
import org.scalajs.dom.URLSearchParams

object MenuRouter:
  def resolve(state: AppState): HtmlElement =
    val menu = new URLSearchParams(window.location.search).get("menu")
    menu match
      case "2" =>
        document.title = "Piccola Italia Menu 2"
        Menu.menu2(state.dishes, state.extraToppings, state.allergens)
      case _ =>
        document.title = "Piccola Italia Menu 1"
        Menu.menu1(state.dishes)
