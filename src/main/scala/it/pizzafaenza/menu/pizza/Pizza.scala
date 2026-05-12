package it.pizzafaenza.menu.pizza

import it.pizzafaenza.menu.menu.{Ingredient, MenuDish, PizzaCategory}
import it.pizzafaenza.menu.allergens.Allergen

case class Pizza(
    id: Int,
    name: String,
    category: PizzaCategory,
    dough: String,
    ingredients: List[Ingredient],
    allergens: List[Allergen],
    price: Double
) extends MenuDish
