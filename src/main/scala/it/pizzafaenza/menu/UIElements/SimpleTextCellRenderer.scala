package it.pizzafaenza.menu.UIElements

import com.raquo.laminar.api.L.*

class SimpleTextCellRenderer(text: String) extends CellRenderer:
  @Override
  def render(heightProportion: Int): Div =
    div(
      cls := "pizza-cell",
      styleAttr := s"--elements-per-column: $heightProportion+2",
      p(
        cls := "coperto-name",
        text
      )
    )
