package com.equalexperts

import org.scalatest.{FeatureSpec, GivenWhenThen, Matchers}

class ShoppingBasketAcceptanceSpec extends FeatureSpec with GivenWhenThen with Matchers {

  feature("Step 1: Add Products to the shopping cart") {

    Given("An empty shopping cart")
    val shoppingCart = ShoppingCart()

    And("a product, Dove Soap with a unit price of 39.99")
    val doveSoap = Product("Dove Soap", 39.99)

    When("The user adds 5 Dove Soaps to the shopping cart")
    val updatedCart = shoppingCart.add(doveSoap, 5)

    Then("The shopping cart should contain 5 Dove Soaps each with a unit price of 39.99")
    updatedCart shouldBe ShoppingCart(List(LineItem(doveSoap, 5)))

    And("the shopping cart’s total price should equal 199.95")
    updatedCart.total shouldBe 199.95
  }


}
