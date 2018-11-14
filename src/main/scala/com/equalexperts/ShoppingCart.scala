package com.equalexperts

import scala.math.BigDecimal.RoundingMode

case class Product(name: String, cost: Double)

case class ShoppingCart(salesTaxRate: Double = 0.00, lineItems: Map[Product, Int] = Map.empty) {

  def add(product: Product, quantity: Int): ShoppingCart = {
    if(quantity > 0) {
      copy(lineItems = updateLineItems(product, quantity))
    } else {
      this
    }
  }

  private def updateLineItems(product: Product, quantity: Int) = {
    lineItems.get(product) match {
      case Some(prevQuantity) => lineItems.updated(product, prevQuantity + quantity)
      case None => lineItems + (product -> quantity)
    }
  }

  def total: Double = round(subTotal + salesTax)

  def salesTax: Double = round(subTotal * salesTaxRate / 100)

  private def subTotal= lineItems.map{ case (product, quantity) => product.cost * quantity }.sum

  private def round(double: Double) = BigDecimal(double).setScale(2, RoundingMode.HALF_UP).doubleValue()
}
