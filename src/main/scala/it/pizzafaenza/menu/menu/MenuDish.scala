package it.pizzafaenza.menu.menu

import it.pizzafaenza.menu.allergens.Allergen

case class Ingredients(italian: List[String], english: List[String])

trait MenuDish:
  def id: Int
  def name: String
  def category: MenuCategory
  def ingredients: Ingredients
  def allergens: List[Allergen]
  def price: Double
