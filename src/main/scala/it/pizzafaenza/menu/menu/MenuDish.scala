package it.pizzafaenza.menu.menu

import it.pizzafaenza.menu.ingredients.Ingredient

trait MenuItem:
  def category: MenuCategory

trait MenuDish extends MenuItem:
  def name: String
  def ingredients: List[Ingredient]
  def price: Double
