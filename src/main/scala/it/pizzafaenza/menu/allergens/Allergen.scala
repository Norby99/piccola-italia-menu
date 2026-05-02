package it.pizzafaenza.menu.allergens

case class Allergen(id: Int, name: String):
  private val basePath = "image/allergens/"
  private val extension = ".png"

  def image: String =
    basePath + name.toLowerCase + extension
