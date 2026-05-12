package it.pizzafaenza.menu.UIElements

import com.raquo.laminar.api.L.*
import it.pizzafaenza.menu.menu.MenuDish
import it.pizzafaenza.menu.utils.{Language, Italian, stringify}

class DishCellRenderer(dish: MenuDish)
    extends CellRenderer:
  @Override
  def render(heightProportion: Int): Div =
    val currentLang: Var[Language] = Var(Italian)
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
          dish.allergens
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
          child.text <-- currentLang.signal.map { lang =>
            dish.ingredients
              .map(
                _.name.get(lang).capitalize
              )
              .mkString(", ")
              .capitalize
          }
        )
      )
    )
