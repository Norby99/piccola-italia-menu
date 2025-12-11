package it.pizzafaenza.menu.pizze

import it.pizzafaenza.menu.Ingredienti.Ingredient

case class Pizza(
    name: String,
    pizzaType: String,
    ingredients: List[Ingredient],
    price: Double
)
