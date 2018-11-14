package com.equalexperts

import org.scalatest.{Matchers, WordSpec}

class ShoppingCartSpec extends WordSpec with Matchers {

  "ShoppingCart" should {
    "be initialised with no line items" in {
      ShoppingCart().lineItems shouldBe empty
    }
  }

  "add" should {

    "result in one product in line items" in {
      val testProduct = Product("Test", 1.00)

      val updatedCart = ShoppingCart().add(testProduct, 1)

      updatedCart.lineItems shouldBe Map(testProduct -> 1)
    }

    "result in no line items if a product with quantity 0 or less is added" in {
      val testProduct = Product("Test", 1.00)

      val updatedCart = ShoppingCart().add(testProduct, 0)

      updatedCart.lineItems shouldBe empty
    }

    "update item quantities as they are added" in {
      val testProduct = Product("Test", 1.00)

      var updatedCart = ShoppingCart().add(testProduct, 1)
      updatedCart = updatedCart.add(testProduct, 1)

      updatedCart.lineItems shouldBe Map(testProduct -> 2)
    }

    "not update item quantities if 0 quantity is added" in {
      val testProduct = Product("Test", 1.00)

      var updatedCart = ShoppingCart().add(testProduct, 1)
      updatedCart = updatedCart.add(testProduct, 0)

      updatedCart.lineItems shouldBe Map(testProduct -> 1)
    }

    "add multiple products to lineitems" in {
      val testProduct1 = Product("Test", 1.00)
      val testProduct2 = Product("Test2", 2.00)

      var updatedCart = ShoppingCart().add(testProduct1, 1)
      updatedCart = updatedCart.add(testProduct2, 1)

      updatedCart.lineItems shouldBe Map(testProduct1 -> 1, testProduct2 -> 1)
    }

    "update multiple products" in {
      val testProduct1 = Product("Test", 1.00)
      val testProduct2 = Product("Test2", 2.00)

      var updatedCart = ShoppingCart().add(testProduct1, 1)
      updatedCart = updatedCart.add(testProduct2, 1)
      updatedCart = updatedCart.add(testProduct2, 2)
      updatedCart = updatedCart.add(testProduct1, 1)

      updatedCart.lineItems shouldBe Map(testProduct1 -> 2, testProduct2 -> 3)
    }

  }

  "total" should {
    "return 2.00 for two items with a value of 1.00 " in {
      val testProduct = Product("Test", 1.00)

      val updatedCart = ShoppingCart().add(testProduct, 2)

      updatedCart.total shouldBe 2.00
    }

    "return 0 for a product with 0 quantity" in {
      val testProduct = Product("Test", 1.00)

      val updatedCart = ShoppingCart().add(testProduct, 0)

      updatedCart.total shouldBe 0.00
    }

    "return 1.56 for one items that cost 1.564" in {
      val testProduct = Product("Test", 1.564)

      val updatedCart = ShoppingCart().add(testProduct, 1)

      updatedCart.total shouldBe 1.56
    }

    "return 1.57 for one items that cost 1.567" in {
      val testProduct = Product("Test", 1.567)

      val updatedCart = ShoppingCart().add(testProduct, 1)

      updatedCart.total shouldBe 1.57
    }

    "return 0.00 for no items" in {
      ShoppingCart().total shouldBe 0.00
    }

    "include sales tax for a specified tax rate" in {
      val testProduct = Product("Test", 200.00)

      val updatedCart = ShoppingCart(10.00).add(testProduct, 1)

      updatedCart.total shouldBe 220.00
    }

  }

  "salesTax" should {

    "return 20.00 for a 200.00 cart with 10% tax" in {
      val testProduct = Product("Test", 200.00)

      val updatedCart = ShoppingCart(10.00).add(testProduct, 1)

      updatedCart.salesTax shouldBe 20.00
    }

    "return 0 for a 200.00 cart with a 0% tax" in {
      val testProduct = Product("Test", 200.00)

      val updatedCart = ShoppingCart(0.00).add(testProduct, 1)

      updatedCart.salesTax shouldBe 0.00
    }
  }
  
}
