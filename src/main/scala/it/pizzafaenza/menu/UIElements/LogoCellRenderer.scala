package it.pizzafaenza.menu.UIElements

import com.raquo.laminar.api.L.*

class LogoCellRenderer extends CellRenderer:
  @Override
  def render(heightProportion: Int = 0): Div =
    div(
      cls := "pizza-cell logo-cell",
      img(src := "image/Piccola-Italia-logo.png", alt := "Pizzeria Faenza Logo")
    )
