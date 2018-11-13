package com.equalexperts

import scala.math.BigDecimal.RoundingMode

case class Product(name: String, cost: Double)

case class LineItem(product: Product, quantity: Int)

case class ShoppingCart(lineItems: List[LineItem] = List.empty) {

  def add(product: Product, quantity: Int): ShoppingCart = {
    if(quantity > 0) {
      ShoppingCart(List(LineItem(product, quantity)))
    } else {
      ShoppingCart()
    }
  }

  def total: Double = round(lineItems.map(item => item.product.cost * item.quantity).sum)

  def round(double: Double): Double = BigDecimal(double).setScale(2, RoundingMode.HALF_UP).doubleValue()
}
