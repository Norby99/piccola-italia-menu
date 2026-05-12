package it.pizzafaezza.menu

import it.pizzafaenza.menu.utils.{AppData, AppState, DataLoader, MenuRouter}
import it.pizzafaenza.menu.language.LanguagePoller
import com.raquo.laminar.api.L.*
import org.scalajs.dom
import org.scalajs.dom.window

import scala.scalajs.concurrent.JSExecutionContext.Implicits.queue

@main def runApp(): Unit =
  val state = AppState.instance

  window.addEventListener(
    "resize",
    (_: dom.Event) =>
      state.windowWidth.set(window.outerWidth)
  )
  state.windowWidth.set(window.outerWidth)

  def applyData(data: AppData): Unit =
    state.dishes.set(data.dishes)
    state.extraToppings.set(data.extraToppings)
    state.allergens.set(data.allergens)

  DataLoader.loadAll().foreach(applyData)

  LanguagePoller.start()

  renderOnDomContentLoaded(
    dom.document.getElementById("app"),
    div(
      EventStream
        .periodic(60 * 1000)
        .flatMapSwitch(_ => EventStream.fromFuture(DataLoader.loadAll()))
        .map(applyData) --> Observer.empty,
      MenuRouter.resolve(state)
    )
  )
