package it.pizzafaenza.menu.menu

sealed trait MenuCategory:
  def title: String

enum PizzaCategory(val title: String) extends MenuCategory:
  case Classiche extends PizzaCategory("Pizze classiche")
  case Bianche extends PizzaCategory("Pizze bianche")
  case Conditissime extends PizzaCategory("Pizze conditissime")
  case Stese extends PizzaCategory("Pizze stese")
  case Napoletano extends PizzaCategory("Impasto Napoletano")
  case Dolci extends PizzaCategory("Pizze dolci")

enum SaladCategory(val title: String) extends MenuCategory:
  case Salad extends SaladCategory("Insalate")

enum ExtraToppingCategory(val title: String) extends MenuCategory:
  case ExtraTopping extends ExtraToppingCategory("Aggiunte")
