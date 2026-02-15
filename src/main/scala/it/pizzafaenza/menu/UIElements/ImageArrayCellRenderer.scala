package it.pizzafaenza.menu.UIElements

import com.raquo.laminar.api.L.*

class ImageArrayCellRenderer(images: Seq[ImageCellRenderer])
    extends CellRenderer:
  @Override
  def render(heightProportion: Int = 0): Div =
    div(
      cls := "pizza-cell horizontal-images",
      styleAttr := s"--elements-per-column: $heightProportion",
      images.map(_.render(heightProportion))
    )
