package it.pizzafaenza.menu.salads

import it.pizzafaenza.menu.ingredients.Ingredient
import it.pizzafaenza.menu.menu.MenuDish

case class Salad(name: String, ingredients: List[Ingredient], price: Double)
    extends MenuDish
