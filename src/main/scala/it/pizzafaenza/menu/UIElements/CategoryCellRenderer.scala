package it.pizzafaenza.menu.UIElements

import com.raquo.laminar.api.L.*
import it.pizzafaenza.menu.menu.DishCategory

class CategoryCellRenderer(category: DishCategory) extends CellRenderer:
  @Override
  def render: Div =
    div(
      cls := "pizza-cell category-cell",
      p(
        cls := "category-name upper-bottom-border",
        category.title
      )
    )
