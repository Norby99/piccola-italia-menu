package it.pizzafaenza.menu.UIElements

import com.raquo.laminar.api.L.*
import it.pizzafaenza.menu.pizze.Pizza

class PizzaCellRenderer(pizza: Pizza) extends CellRenderer:
  @Override
  def render: Div =
    div(
      cls := "pizza-cell",
      div(
        cls := "pizza-header",
        p(
          cls := "pizza-name",
          s"${pizza.name}"
        ),
        p(
          cls := "pizza-price",
        )
      ),
      div(
        cls := "pizza-body",
        p(
          pizza.ingredients.map(i => i.name.italian).mkString(", ").capitalize
        )
      )
    )
