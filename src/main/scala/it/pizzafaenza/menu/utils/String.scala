package it.pizzafaenza.menu.utils

extension [N](n: N)(using num: Numeric[N])
  def stringify: String = f"${num.toDouble(n)}%.2f"
