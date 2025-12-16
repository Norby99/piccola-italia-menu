package it.pizzafaenza.menu.UIElements

import com.raquo.laminar.api.L.*
import it.pizzafaenza.menu.pizze.Pizza

class PizzaCellRenderer(pizza: Pizza):
  def render: Div =
    div(
      h3(s"${pizza.name} - €${pizza.price}"),
      p(s"Tipo: ${pizza.pizzaType}"),
      h4("Ingredienti:"),
      ul(
        pizza.ingredients.map { ingr =>
          li(s"${ingr.name.italian} (${ingr.allergen.name})")
        }
      )
    )
