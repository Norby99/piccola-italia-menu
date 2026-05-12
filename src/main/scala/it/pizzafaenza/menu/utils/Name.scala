package it.pizzafaenza.menu.utils

case class Name(italian: String, english: String):
  def get(lang: Language): String = lang match
    case Italian => italian
    case English => english
