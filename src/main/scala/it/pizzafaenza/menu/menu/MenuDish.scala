package it.pizzafaenza.menu.menu

import it.pizzafaenza.menu.ingredients.Ingredient

trait MenuDish:
  def name: String
  def ingredients: List[Ingredient]
  def price: Double
