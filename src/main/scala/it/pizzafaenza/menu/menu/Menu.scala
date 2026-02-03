package it.pizzafaenza.menu.menu

import com.raquo.laminar.api.L.*
import it.pizzafaenza.menu.UIElements.{
  CategoryCellRenderer,
  CellRenderer,
  DishCellRenderer
}

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

  def menu2(dishes: Var[List[MenuDish]]): Div =
    val orderMap: Map[MenuCategory, Int] = Map(
      PizzaCategory.Napoletano -> 1,
      PizzaCategory.Dolci -> 2,
      SaladCategory.Salad -> 3
    )

    val pizzaList = createPizzaList(dishes, orderMap)
    createUI(pizzaList, columnCount = 4, rowCount = 14)

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

  private def createUI(
      dishList: Signal[Seq[CellRenderer]],
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
      children <-- dishList.map(_.map(_.render(rowCount)))
    )
