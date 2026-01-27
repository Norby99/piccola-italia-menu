package it.pizzafaenza.menu.pizza

import it.pizzafaenza.menu.ingredients.Ingredient
import it.pizzafaenza.menu.menu.MenuDish

case class Pizza(
    name: String,
    category: PizzaCategory,
    ingredients: List[Ingredient],
    price: Double
) extends MenuDish
