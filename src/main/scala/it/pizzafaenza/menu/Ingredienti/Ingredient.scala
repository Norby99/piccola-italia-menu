package it.pizzafaenza.menu.Ingredienti

case class Name(italian: String, english: String)
case class Allergen(name: String)

case class Ingredient(id: Int, name: Name, allergen: Allergen)
