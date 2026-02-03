package it.pizzafaenza.menu.UIElements

import com.raquo.laminar.api.L.*
import it.pizzafaenza.menu.menu.MenuCategory

class CategoryCellRenderer(category: MenuCategory) extends CellRenderer:
  @Override
  def render(heightProportion: Int): Div =
    div(
      cls := "pizza-cell category-cell",
      onMountCallback(ctx =>
        ctx.thisNode.ref.style.setProperty(
          "--elements-per-column",
          heightProportion.toString
        )
      ),
      p(
        cls := "category-name upper-bottom-border",
        category.title
      )
    )
