package com.equalexperts

import org.scalatest.{FeatureSpec, GivenWhenThen, Matchers}

class ShoppingCartAcceptanceSpec extends FeatureSpec with GivenWhenThen with Matchers {

  feature("A Shopping Cart") {

    scenario("Step 1: Add Products to the shopping cart") {

      Given("An empty shopping cart")
      val shoppingCart = ShoppingCart()

      And("a product, Dove Soap with a unit price of 39.99")
      val doveSoap = Product("Dove Soap", 39.99)

      When("The user adds 5 Dove Soaps to the shopping cart")
      val updatedCart = shoppingCart.add(doveSoap, 5)

      Then("The shopping cart should contain 5 Dove Soaps each with a unit price of 39.99")
      updatedCart.lineItems should contain(doveSoap -> 5)

      And("the shopping cart’s total price should equal 199.95")
      updatedCart.total shouldBe 199.95
    }

    scenario("Step 2: Add additional products of the same type to the shopping cart") {

      Given("An empty shopping cart")
      val shoppingCart = ShoppingCart()

      And("a product, Dove Soap with a unit price of 39.99")
      val doveSoap = Product("Dove Soap", 39.99)

      When("The user adds 5 Dove Soaps to the shopping cart")
      var updatedCart = shoppingCart.add(doveSoap, 5)

      And("adds another 3 Dove Soaps to the cart")
      updatedCart = updatedCart.add(doveSoap, 3)

      Then("The shopping cart should contain 8 Dove Soaps each with a unit price of 39.99")
      updatedCart.lineItems should contain(doveSoap -> 8)

      And("the shopping cart’s total price should equal 319.92")
      updatedCart.total shouldBe 319.92
    }

    scenario("Step 3: Calculate the tax rate of the shopping cart with multiple items") {

      Given("An empty shopping cart with a sales tax rate of 12.5%")
      val shoppingCart = ShoppingCart(salesTaxRate = 12.5)

      And("a product, Dove Soap with a unit price of 39.99")
      val doveSoap = Product("Dove Soap", 39.99)

      And("a product, Axe Deo with a unit price of 99.99")
      val axeDeo = Product("Axe Deo", 99.99)

      When("The user adds 2 Dove Soaps to the shopping cart")
      var updatedCart = shoppingCart.add(doveSoap, 2)

      And("and then adds 2 Axe Deos to the cart")
      updatedCart = updatedCart.add(axeDeo, 2)

      Then("The shopping cart should contain 2 Dove Soaps each with a unit price of 39.99")
      updatedCart.lineItems should contain(doveSoap -> 2)

      And("The shopping cart should contain 2 Axe Deos each with a unit price of 99.99")
      updatedCart.lineItems should contain(axeDeo -> 2)

      And("the total sales tax amount for the shopping cart should equal 35.00")
      updatedCart.salesTax shouldBe 35.00

      And("the shopping cart’s total price should equal 314.96")
      updatedCart.total shouldBe 314.96
    }

  }


}
