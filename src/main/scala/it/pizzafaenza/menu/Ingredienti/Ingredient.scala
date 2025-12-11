package it.pizzafaenza.menu.Ingredienti

sealed case class Name(italian: String, english: String)

case class Ingredient(id: Int, name: Name)
