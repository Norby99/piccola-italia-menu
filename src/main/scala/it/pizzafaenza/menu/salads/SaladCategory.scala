package it.pizzafaenza.menu.salads

import it.pizzafaenza.menu.menu.DishCategory

enum SaladCategory(val title: String) extends DishCategory:
  case Vegetarian extends SaladCategory("Insalate")
