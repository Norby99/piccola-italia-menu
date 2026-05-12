package it.pizzafaenza.menu.UIElements

import com.raquo.laminar.api.L.*
import it.pizzafaenza.menu.extraToppings.ExtraTopping
import it.pizzafaenza.menu.language.{CurrentLanguage, Language}
import it.pizzafaenza.menu.utils.stringify

class ExtraToppingCellRenderer(extTopping: ExtraTopping) extends CellRenderer:

  /** Renders the extra topping cell with its name and price.
    * @param heightProportion
    *   it is not actually used in this renderer.
    * @return
    */
  @Override
  def render(heightProportion: Int = 0): Div =
    div(
      cls := "extra-topping-cell",
      div(
        cls := "pizza-header",
        p(
          cls := "extra-topping-name",
          child.text <-- CurrentLanguage.currentLang.signal.map { lang =>
            s"${extTopping.name.get(lang)}"
          }
        ),
        p(
          cls := "pizza-price",
          s"€${extTopping.price.stringify}"
        )
      )
    )
