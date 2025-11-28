import com.raquo.laminar.api.L._
import org.scalajs.dom

@main def runApp(): Unit =
  val app = div(
    navTag(
      a(href := "#home", "Home"),
      " | ",
      a(href := "#about", "About")
    ),
    hr(),
    child <-- Var("Benvenuto nella mini SPA Laminar!").signal.map(text =>
      p(text)
    )
  )

  renderOnDomContentLoaded(dom.document.getElementById("app"), app)
