package it.pizzafaenza.menu.ingredients

import it.pizzafaenza.menu.utils.Name
import it.pizzafaenza.menu.allergens.Allergen

case class Ingredient(id: Int, name: Name, allergen: Option[Allergen])
