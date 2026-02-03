package it.pizzafaenza.menu.UIElements

import com.raquo.laminar.api.L.*
import it.pizzafaenza.menu.menu.MenuCategory

class CategoryCellRenderer(category: MenuCategory) extends CellRenderer:
  @Override
  def render(rowCount: Int): Div =
    div(
      cls := "pizza-cell category-cell",
      p(
        cls := "category-name upper-bottom-border",
        category.title
      )
    )
