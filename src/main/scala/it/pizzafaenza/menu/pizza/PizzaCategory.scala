package it.pizzafaenza.menu.pizze

enum PizzaCategory(val description: String):
  case Classiche extends PizzaCategory("Pizze classiche")
  case Bianche extends PizzaCategory("Pizze bianche")
  case Conditissime extends PizzaCategory("Pizze conditissime")
  case Stese extends PizzaCategory("Pizze stese")
  case Napoletano extends PizzaCategory("Impasto Napoletano")
  case Dolci extends PizzaCategory("Pizze dolci")
