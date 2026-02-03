package it.pizzafaenza.menu.UIElements

import com.raquo.laminar.api.L.*
import it.pizzafaenza.menu.extraToppings.ExtraTopping
import it.pizzafaenza.menu.utils.stringify

class ExtraToppingCellRenderer(extTopping: ExtraTopping) extends CellRenderer:
  @Override
  def render(heightProportion: Int): Div =
    div(
      cls := "pizza-cell",
      onMountCallback(ctx =>
        ctx.thisNode.ref.style.setProperty(
          "--elements-per-column",
          heightProportion.toString
        )
      ),
      div(
        cls := "pizza-header",
        p(
          cls := "pizza-name",
          s"${extTopping.name}"
        ),
        p(
          cls := "pizza-price",
          s"€${extTopping.price.stringify}"
        )
      )
    )
