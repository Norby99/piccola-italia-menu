package it.pizzafaenza.menu.language

import com.raquo.laminar.api.L.Var

sealed trait Language
case object Italian extends Language
case object English extends Language

object CurrentLanguage:
  val currentLang: Var[Language] = Var(Italian)
