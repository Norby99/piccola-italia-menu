package it.pizzafaenza.menu.menu

import com.raquo.laminar.api.L.*
import it.pizzafaenza.menu.UIElements.{CategoryCellRenderer, PizzaCellRenderer}
import it.pizzafaenza.menu.pizza.{Pizza, PizzaCategory}

object Menu:
  def menu1(pizze: Var[List[Pizza]]): Div =
    val orderMap = Map(
      PizzaCategory.Classiche -> 1,
      PizzaCategory.Bianche -> 2,
      PizzaCategory.Conditissime -> 3,
      PizzaCategory.Stese -> 4
    )

    val pizzeList = createMenu(pizze, orderMap)
    createUI(pizzeList, columnCount = 5, rowCount = 15)

  def menu2(pizze: Var[List[Pizza]]): Div =
    val orderMap = Map(
      PizzaCategory.Napoletano -> 1,
      PizzaCategory.Dolci -> 2
    )

    val pizzeList = createMenu(pizze, orderMap)
    createUI(pizzeList, columnCount = 4, rowCount = 14)

  private def createMenu(
      pizze: Var[List[Pizza]],
      orderMap: Map[PizzaCategory, Int]
  ): Signal[Seq[(PizzaCategory, List[Pizza])]] =
    pizze.signal.map { p =>
      p
        .filter(p => orderMap.contains(p.category))
        .groupBy(_.category)
        .toSeq
        .sortBy { case (category, _) =>
          orderMap.getOrElse(category, Int.MaxValue)
        }
    }

  private def createUI(
      pizzeList: Signal[Seq[(PizzaCategory, List[Pizza])]],
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
      children <-- pizzeList.map { grouped =>
        grouped.flatMap { case (category, pizze) =>
          CategoryCellRenderer(category).render +: pizze.map { pizza =>
            PizzaCellRenderer(pizza, rowCount).render
          }
        }
      }
    )
