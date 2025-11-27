import org.scalajs.dom

object Example:
  def main(args: Array[String]): Unit =
    val hello = dom.document.createElement("p")
    hello.textContent = "Hello from Scala.js!"

    dom.document.body.appendChild(hello)
    dom.console.log("Hello from Scala.js (console)")
