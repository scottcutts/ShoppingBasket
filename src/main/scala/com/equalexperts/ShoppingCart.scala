package com.equalexperts

import scala.math.BigDecimal.RoundingMode

case class Product(name: String, cost: Double)

case class LineItem(product: Product, quantity: Int)

case class ShoppingCart(lineItems: List[LineItem] = List.empty) {

  def add(product: Product, quantity: Int): ShoppingCart = {
    val updatedLineItems = if(quantity > 0) {
      updateLineItems(product, quantity)
    } else {
      lineItems
    }

    ShoppingCart(updatedLineItems)
  }

  private def updateLineItems(product: Product, quantity: Int) = {
    val totalQuantity = lineItems.find(_.product == product)
      .fold(quantity)(_.quantity + quantity)

    List(LineItem(product, totalQuantity))
  }

  def total: Double = round(lineItems.map(item => item.product.cost * item.quantity).sum)

  def salesTax: Double = ???

  private def round(double: Double) = BigDecimal(double).setScale(2, RoundingMode.HALF_UP).doubleValue()
}
