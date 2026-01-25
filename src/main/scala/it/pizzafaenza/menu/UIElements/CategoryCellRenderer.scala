package it.pizzafaenza.menu.UIElements

import it.pizzafaenza.menu.pizza.PizzaCategory

import com.raquo.laminar.api.L.*

class CategoryCellRenderer(category: PizzaCategory) extends CellRenderer:
  @Override
  def render: Div =
    div(
      cls := "pizza-cell category-cell",
      p(
        cls := "category-name upper-bottom-border",
        category.description
      )
    )
