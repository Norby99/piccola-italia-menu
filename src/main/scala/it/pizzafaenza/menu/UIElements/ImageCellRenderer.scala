package it.pizzafaenza.menu.UIElements

import com.raquo.laminar.api.L.*

class ImageCellRenderer(imagePath: String, inGrid: Boolean = true)
    extends CellRenderer:
  @Override
  def render(heightProportion: Int = 0): Div =
    div(
      cls := (if inGrid then "pizza-cell square-cell" else "square-cell"),
      img(src := imagePath, alt := "Missing Image")
    )
