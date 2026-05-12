package it.pizzafaenza.menu.utils

import com.raquo.laminar.api.L.*
import it.pizzafaenza.menu.menu.Menu
import org.scalajs.dom.window
import org.scalajs.dom.URLSearchParams

object MenuRouter:
  def resolve(state: AppState): HtmlElement =
    new URLSearchParams(window.location.search).get("menu") match
      case "2" =>
        Menu.menu2(state.dishes, state.extraToppings, state.allergens)
      case _ =>
        Menu.menu1(state.dishes)
