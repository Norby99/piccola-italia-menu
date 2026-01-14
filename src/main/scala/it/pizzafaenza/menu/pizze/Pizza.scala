package it.pizzafaenza.menu.pizze

import it.pizzafaenza.menu.Ingredienti.Ingredient

case class Pizza(
    name: String,
    category: PizzaCategory,
    ingredients: List[Ingredient],
    price: Double
)
