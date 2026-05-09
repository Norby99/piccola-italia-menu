package it.pizzafaenza.menu.salads

import it.pizzafaenza.menu.menu.{Ingredients, MenuDish, SaladCategory}
import it.pizzafaenza.menu.allergens.Allergen

case class Salad(
    id: Int,
    name: String,
    category: SaladCategory,
    ingredients: Ingredients,
    allergens: List[Allergen],
    price: Double
) extends MenuDish
