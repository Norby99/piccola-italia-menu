package it.pizzafaenza.menu.menu

import it.pizzafaenza.menu.allergens.Allergen
import it.pizzafaenza.menu.utils.Name

case class Ingredient(name: Name)

trait MenuDish:
  def id: Int
  def name: String
  def category: MenuCategory
  def ingredients: List[Ingredient]
  def allergens: List[Allergen]
  def price: Double
