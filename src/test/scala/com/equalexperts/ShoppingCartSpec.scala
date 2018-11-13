package com.equalexperts

import org.scalatest.{Matchers, WordSpec}

class ShoppingCartSpec extends WordSpec with Matchers {

  "ShoppingCart" should {

    "be initialised with no line items" in {
      ShoppingCart().lineItems shouldBe empty
    }

    "add a one product to its line items" in {
      val testProduct = Product("Test", 1.00)

      val updatedCart = ShoppingCart().add(testProduct, 1)

      updatedCart.lineItems shouldBe List(LineItem(testProduct, 1))
    }

    "have no line items if a product with quantity 0 or less is added" in {
      val testProduct = Product("Test", 1.00)

      val updatedCart = ShoppingCart().add(testProduct, 0)

      updatedCart.lineItems shouldBe empty
    }

    "return a total of 2.00 for two items with a value of 1.00 " in {
      val testProduct = Product("Test", 1.00)

      val updatedCart = ShoppingCart().add(testProduct, 2)

      updatedCart.total shouldBe 2.00
    }

    "return a total of 0 for a product with 0 quantity" in {
      val testProduct = Product("Test", 1.00)

      val updatedCart = ShoppingCart().add(testProduct, 0)

      updatedCart.total shouldBe 0.00
    }

    "return a total of 1.56 for one items that cost 1.564" in {
      val testProduct = Product("Test", 1.564)

      val updatedCart = ShoppingCart().add(testProduct, 1)

      updatedCart.total shouldBe 1.56
    }

    "return a total of 1.57 for one items that cost 1.567" in {
      val testProduct = Product("Test", 1.567)

      val updatedCart = ShoppingCart().add(testProduct, 1)

      updatedCart.total shouldBe 1.57
    }

    "have a total of 0.00 for no items" in {
      ShoppingCart().total shouldBe 0.00
    }

  }
  
}
