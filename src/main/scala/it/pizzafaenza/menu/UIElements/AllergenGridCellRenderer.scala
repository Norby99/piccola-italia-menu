package it.pizzafaenza.menu.UIElements

import it.pizzafaenza.menu.allergens.Allergen

import com.raquo.laminar.api.L.*

class AllergenGridCellRenderer(allergens: List[Allergen]) extends CellRenderer:
  @Override
  def render(heightProportion: Int): Div =
    val rows = math.max(1, math.ceil(allergens.size / 2.0).toInt)
    div(
      cls := "pizza-cell allergen-grid-cell",
      styleAttr := s"height: calc($rows * 100% / $heightProportion);",
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
