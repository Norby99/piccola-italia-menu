package it.pizzafaenza.menu.utils

import it.pizzafaenza.menu.language.{English, Italian, Language}

case class Name(italian: String, english: String):
  def get(lang: Language): String = lang match
    case Italian => italian
    case English => english
