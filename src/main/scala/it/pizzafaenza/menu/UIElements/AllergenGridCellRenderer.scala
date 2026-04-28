package it.pizzafaenza.menu.UIElements

import it.pizzafaenza.menu.allergens.Allergen

import com.raquo.laminar.api.L.*

class AllergenGridCellRenderer(allergens: List[Allergen]) extends CellRenderer:
  @Override
  def render(heightProportion: Int): Div =
    div(
      cls := "allergen-grid-cell",
      div(
        cls := "allergen-grid",
        allergens.zipWithIndex.map { case (allergen, index) =>
          val isFirstColumn = index % 2 == 0
          div(
            cls := s"allergen-item ${
                if (isFirstColumn) "col-left" else "col-right"
              }",
            if (isFirstColumn) Seq(
              span(cls := "allergen-name", allergen.name),
              img(
                cls := "allergen-image",
                src := allergen.image
              )
            )
            else Seq(
              img(
                cls := "allergen-image",
                src := allergen.image
              ),
              span(cls := "allergen-name", allergen.name)
            )
          )
        }
      )
    )
