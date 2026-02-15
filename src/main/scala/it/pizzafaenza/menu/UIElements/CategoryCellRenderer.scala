package it.pizzafaenza.menu.UIElements

import com.raquo.laminar.api.L.*
import it.pizzafaenza.menu.menu.MenuCategory

class CategoryCellRenderer(category: MenuCategory) extends CellRenderer:
  @Override
  def render(heightProportion: Int): Div =
    div(
      cls := "pizza-cell category-cell",
      styleAttr := s"--elements-per-column: $heightProportion",
      p(
        cls := "category-name upper-bottom-border",
        category.title
      )
    )
