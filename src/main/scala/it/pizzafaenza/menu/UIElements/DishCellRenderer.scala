package it.pizzafaenza.menu.UIElements

import com.raquo.laminar.api.L.*
import it.pizzafaenza.menu.menu.MenuDish
import it.pizzafaenza.menu.utils.stringify

class DishCellRenderer(dish: MenuDish)
    extends CellRenderer:
  @Override
  def render(heightProportion: Int): Div =
    div(
      cls := "pizza-cell",
      styleAttr := s"--elements-per-column: $heightProportion",
      div(
        cls := "pizza-header",
        p(
          cls := "pizza-name",
          s"${dish.name}"
        ),
        p(
          cls := "pizza-allergen-grid",
          dish.ingredients
            .flatMap(_.allergen)
            .map(_.image)
            .distinct
            .map(imagePath =>
              img(
                src := imagePath,
                cls := "pizza-allergen",
                alt := "Allergen"
              )
            ).toSeq
        ),
        p(
          cls := "pizza-price",
          s"€${dish.price.stringify}"
        )
      ),
      div(
        cls := "pizza-body",
        p(
          cls := "pizza-ingredients",
          dish.ingredients.map(i => i.name.italian).mkString(", ").capitalize
        )
      )
    )
