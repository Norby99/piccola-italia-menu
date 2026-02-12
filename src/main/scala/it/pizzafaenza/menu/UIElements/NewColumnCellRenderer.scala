package it.pizzafaenza.menu.UIElements

import com.raquo.laminar.api.L.*

class NewColumnCellRenderer extends CellRenderer:
  @Override
  def render(heightProportion: Int): Div =
    div(
      cls := "pizza-cell new-column-cell"
    )
