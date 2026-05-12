package it.pizzafaenza.menu.utils

import com.raquo.laminar.api.L.*
import it.pizzafaenza.menu.allergens.Allergen
import it.pizzafaenza.menu.extraToppings.ExtraTopping
import it.pizzafaenza.menu.menu.MenuDish

case class AppState(
    dishes: Var[List[MenuDish]] = Var(List.empty),
    extraToppings: Var[List[ExtraTopping]] = Var(List.empty),
    allergens: Var[List[Allergen]] = Var(List.empty),
    windowWidth: Var[Int] = Var(0)
)

object AppState:
  val instance: AppState = AppState()
