package it.pizzafaenza.menu.pizze

case class Pizza(
    name: String,
    pizzaType: String,
    ingredients: List[String],
    price: Double
)
