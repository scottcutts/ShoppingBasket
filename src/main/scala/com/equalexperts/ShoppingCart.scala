package com.equalexperts

case class Product(name: String, cost: Double)

case class LineItem(product: Product, quantity: Int)

case class ShoppingCart(lineItems: List[LineItem] = List.empty) {

  def add(product: Product, quantity: Int): ShoppingCart = ???

  def total: Double = ???
}
