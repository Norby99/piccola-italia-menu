package it.pizzafaenza.menu.menu

import com.raquo.laminar.api.L.*
import it.pizzafaenza.menu.UIElements.{
  AllergenGridCellRenderer,
  CategoryCellRenderer,
  CellRenderer,
  DishCellRenderer,
  ExtraToppingCellRenderer,
  ImageArrayCellRenderer,
  ImageCellRenderer,
  NewColumnCellRenderer
}
import it.pizzafaenza.menu.allergens.Allergen
import it.pizzafaenza.menu.extraToppings.ExtraTopping

object Menu:
  def menu1(dishes: Var[List[MenuDish]]): Div =
    val orderMap: Map[MenuCategory, Int] = Map(
      PizzaCategory.Classiche -> 1,
      PizzaCategory.Bianche -> 2,
      PizzaCategory.Conditissime -> 3,
      PizzaCategory.Stese -> 4
    )

    val pizzaList = createPizzaList(dishes, orderMap)
    createUI(pizzaList, columnCount = 5, rowCount = 15)

  def menu2(
      dishes: Var[List[MenuDish]],
      extToppings: Var[List[ExtraTopping]]
  ): Div =
    val orderMap: Map[MenuCategory, Int] = Map(
      PizzaCategory.Napoletano -> 1,
      PizzaCategory.Dolci -> 2,
      SaladCategory.Salad -> 3
    )

    val pizzaList = createPizzaList(dishes, orderMap)
    val extraToppingList = createExtraToppingList(extToppings)
    val legendGrid = createLegend
    val combinedList =
      Signal.combine(pizzaList, extraToppingList, legendGrid).map {
        case (pizzas, toppings, legend) =>
          pizzas ++
            Seq(NewColumnCellRenderer()) ++
            toppings ++
            Seq(NewColumnCellRenderer()) ++
            Seq(createLogo) ++
            Seq(createSocialLogos) ++
            legend
      }
    createUI(combinedList, columnCount = 4, rowCount = 14)

  private def createPizzaList(
      dishes: Var[List[MenuDish]],
      orderMap: Map[MenuCategory, Int] = Map.empty
  ): Signal[Seq[CellRenderer]] =
    dishes.signal.map { p =>
      p
        .filter(p => orderMap.contains(p.category))
        .groupBy(_.category)
        .toSeq
        .sortBy { case (category, _) =>
          orderMap.getOrElse(category, Int.MaxValue)
        }
        .flatMap { case (category, dishList) =>
          CategoryCellRenderer(category) +: dishList.map(DishCellRenderer(_))
        }
    }

  private def createExtraToppingList(
      dishes: Var[List[ExtraTopping]]
  ): Signal[Seq[CellRenderer]] =
    dishes.signal.map { p =>
      if (p.isEmpty) Seq.empty
      else
        CategoryCellRenderer(ExtraToppingCategory.ExtraTopping) +: p.map(
          ExtraToppingCellRenderer(_)
        )
    }

  private def createLogo: CellRenderer =
    ImageCellRenderer("image/Piccola-Italia-logo.png")

  private def createSocialLogos: CellRenderer =
    ImageArrayCellRenderer(Seq(
      ImageCellRenderer("image/socials/Facebook-logo.png"),
      ImageCellRenderer("image/socials/Instagram-logo.png"),
      ImageCellRenderer("image/socials/TripAdvisor-Logo.png")
    ))

  private def createLegend: Signal[Seq[CellRenderer]] =
    val allergenList = List(
      Allergen("Glutine", "image/allergens/glutine.png"),
      Allergen("Latticini", "image/allergens/latticini.png"),
      Allergen("Glutine", "image/allergens/glutine.png"),
      Allergen("Latticini", "image/allergens/latticini.png"),
      Allergen("Glutine", "image/allergens/glutine.png"),
      Allergen("Latticini", "image/allergens/latticini.png"),
      Allergen("Glutine", "image/allergens/glutine.png"),
      Allergen("Latticini", "image/allergens/latticini.png")
    )
    Val(Seq(
      CategoryCellRenderer(LegendCategory.Legend),
      AllergenGridCellRenderer(allergenList)
    )).signal

  private def createUI(
      cellList: Signal[Seq[CellRenderer]],
      columnCount: Int = 5,
      rowCount: Int = 15
  ): Div =
    div(
      cls := "pizze full-screen-margin pizze-grid",
      onMountCallback(ctx =>
        ctx.thisNode.ref.style.setProperty(
          "--column-count",
          columnCount.toString
        )
      ),
      children <-- cellList.map(_.map(_.render(rowCount)))
    )
