package it.pizzafaenza.menu.salads

import it.pizzafaenza.menu.ingredients.Ingredient
import it.pizzafaenza.menu.menu.{MenuDish, SaladCategory}

case class Salad(
    name: String,
    category: SaladCategory,
    ingredients: List[Ingredient],
    price: Double
) extends MenuDish
