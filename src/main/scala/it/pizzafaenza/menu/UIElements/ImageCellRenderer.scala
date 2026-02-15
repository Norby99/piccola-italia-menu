package it.pizzafaenza.menu.UIElements

import com.raquo.laminar.api.L.*

class ImageCellRenderer(imagePath: String) extends CellRenderer:
  @Override
  def render(heightProportion: Int = 0): Div =
    div(
      cls := "pizza-cell logo-cell",
      img(src := imagePath, alt := "Missing Image")
    )
