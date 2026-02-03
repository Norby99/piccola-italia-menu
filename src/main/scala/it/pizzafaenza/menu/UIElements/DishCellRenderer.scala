package it.pizzafaenza.menu.UIElements

import com.raquo.laminar.api.L.*
import it.pizzafaenza.menu.menu.MenuDish
import it.pizzafaenza.menu.utils.stringify

class DishCellRenderer(dish: MenuDish)
    extends CellRenderer:
  @Override
  def render(rowCount: Int): Div =
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
          s"${dish.name}"
        ),
        p(
          cls := "pizza-price",
          s"€${dish.price.stringify}"
        )
      ),
      div(
        cls := "pizza-body",
        p(
          cls := "pizza-ingredients",
          dish.ingredients.map(i => i.name.italian).mkString(", ").capitalize
        )
      )
    )
