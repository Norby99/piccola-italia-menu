package it.pizzafaenza.menu.pizza

import it.pizzafaenza.menu.menu.{Ingredients, MenuDish, PizzaCategory}
import it.pizzafaenza.menu.allergens.Allergen

case class Pizza(
    id: Int,
    name: String,
    category: PizzaCategory,
    dough: String,
    ingredients: Ingredients,
    allergens: List[Allergen],
    price: Double
) extends MenuDish
