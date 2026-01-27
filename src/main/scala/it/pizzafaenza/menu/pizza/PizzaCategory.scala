package it.pizzafaenza.menu.pizza

import it.pizzafaenza.menu.menu.DishCategory

enum PizzaCategory(val title: String) extends DishCategory:
  case Classiche extends PizzaCategory("Pizze classiche")
  case Bianche extends PizzaCategory("Pizze bianche")
  case Conditissime extends PizzaCategory("Pizze conditissime")
  case Stese extends PizzaCategory("Pizze stese")
  case Napoletano extends PizzaCategory("Impasto Napoletano")
  case Dolci extends PizzaCategory("Pizze dolci")
