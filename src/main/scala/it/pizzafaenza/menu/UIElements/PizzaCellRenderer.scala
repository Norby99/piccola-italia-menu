package it.pizzafaenza.menu.UIElements

import com.raquo.laminar.api.L.*
import it.pizzafaenza.menu.pizza.Pizza
import it.pizzafaenza.menu.utils.stringify

class PizzaCellRenderer(pizza: Pizza, rowCount: Int)
    extends CellRenderer:
  @Override
  def render: Div =
    div(
      cls := "pizza-cell",
      onMountCallback(ctx =>
        ctx.thisNode.ref.style.setProperty(
          "--elements-per-column",
          rowCount.toString
        )
      ),
      div(
        cls := "pizza-header",
        p(
          cls := "pizza-name",
          s"${pizza.name}"
        ),
        p(
          cls := "pizza-price",
          s"€${pizza.price.stringify}"
        )
      ),
      div(
        cls := "pizza-body",
        p(
          cls := "pizza-ingredients",
          pizza.ingredients.map(i => i.name.italian).mkString(", ").capitalize
        )
      )
    )
