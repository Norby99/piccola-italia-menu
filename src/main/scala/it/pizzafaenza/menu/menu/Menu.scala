package it.pizzafaenza.menu.menu

import com.raquo.laminar.api.L.*
import it.pizzafaenza.menu.UIElements.{CategoryCellRenderer, DishCellRenderer}
import it.pizzafaenza.menu.pizza.Pizza
import it.pizzafaenza.menu.salads.Salad

object Menu:
  def menu1(dishes: Var[List[MenuDish]]): Div =
    val orderMap: Map[MenuCategory, Int] = Map(
      PizzaCategory.Classiche -> 1,
      PizzaCategory.Bianche -> 2,
      PizzaCategory.Conditissime -> 3,
      PizzaCategory.Stese -> 4
    )

    val menuList = createMenuList(dishes, orderMap)
    createUI(menuList, columnCount = 5, rowCount = 15)

  def menu2(dishes: Var[List[MenuDish]]): Div =
    val orderMap: Map[MenuCategory, Int] = Map(
      PizzaCategory.Napoletano -> 1,
      PizzaCategory.Dolci -> 2,
      SaladCategory.Salad -> 3
    )

    val menuList = createMenuList(dishes, orderMap)
    createUI(menuList, columnCount = 4, rowCount = 14)

  private def createMenuList(
      dishes: Var[List[MenuDish]],
      orderMap: Map[MenuCategory, Int] = Map.empty
  ): Signal[Seq[(MenuCategory, List[MenuDish])]] =
    dishes.signal.map { p =>
      p
        .filter(p => orderMap.contains(p.category))
        .groupBy(_.category)
        .toSeq
        .sortBy { case (category, _) =>
          orderMap.getOrElse(category, Int.MaxValue)
        }
    }

  private def createUI(
      dishList: Signal[Seq[(MenuCategory, List[MenuDish])]],
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
      children <-- dishList.map { grouped =>
        grouped.flatMap { case (category, dish) =>
          CategoryCellRenderer(category).render +: dish.map { d =>
            DishCellRenderer(d, rowCount).render
          }
        }
      }
    )
