package it.pizzafaenza.menu.ingredients

import it.pizzafaenza.menu.utils.Name

case class Allergen(name: String)

case class Ingredient(id: Int, name: Name, allergen: Allergen)
