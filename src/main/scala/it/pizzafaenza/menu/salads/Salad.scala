package it.pizzafaenza.menu.salads

import it.pizzafaenza.menu.menu.{Ingredient, MenuDish, SaladCategory}
import it.pizzafaenza.menu.allergens.Allergen

case class Salad(
    id: Int,
    name: String,
    category: SaladCategory,
    ingredients: List[Ingredient],
    allergens: List[Allergen],
    price: Double
) extends MenuDish
