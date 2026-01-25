package it.pizzafaenza.menu.pizza

import it.pizzafaenza.menu.ingredients.Ingredient

case class Pizza(
    name: String,
    category: PizzaCategory,
    ingredients: List[Ingredient],
    price: Double
)
