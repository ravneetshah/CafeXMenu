package test.scala.cafeX

import org.scalatest.FunSuite
import org.scalatest.BeforeAndAfter
import main.scala.cafeX.CafeXMenu

class TestCafeXMenu extends FunSuite with BeforeAndAfter {
  
  var testCafeXMenu:CafeXMenu = _
  
  before {
    testCafeXMenu = new CafeXMenu()
  }
  
  test("order Cola and Coffee") {
    testCafeXMenu.loadMenu()
    val orderedItems = Array("Cola", "Coffee")
    assert(testCafeXMenu.calculateBill(orderedItems) == 1.50)
  }
  
  test("no order placed") {
    testCafeXMenu.loadMenu()
    val orderedItems = Array.empty[String]
    val thrown = intercept[IllegalArgumentException] {
      testCafeXMenu.calculateBill(orderedItems)
    }
    assert(thrown.getMessage == "No items ordered")
  }
  
  test("incorrect item ordered") {
    testCafeXMenu.loadMenu()
    val orderedItems = Array("Cola", "Coffee", "wrongItem")
    val thrown = intercept[IllegalArgumentException] {
      testCafeXMenu.calculateBill(orderedItems)
    }
    assert(thrown.getMessage == "Invalid item passed: wrongItem")
  }
  
  test("only drinks ordered") {
    testCafeXMenu.loadMenu()
    val orderedItems = Array("Cola", "Coffee")
    assert(testCafeXMenu.calculateBill(orderedItems) == 1.50)
  }
  
  test("hot food ordered") {
    testCafeXMenu.loadMenu()
    val orderedItems = Array("Cola", "Coffee", "Cheese Sandwich")
    assert(testCafeXMenu.calculateBill(orderedItems) == 4.20)
  }
  
  test("cold food ordered") {
    testCafeXMenu.loadMenu()
    val orderedItems = Array("Cola", "Cheese Sandwich")
    assert(testCafeXMenu.calculateBill(orderedItems) == 2.75)
  }
  
  test("maximum 20£ service charge") {
    testCafeXMenu.loadMenu()
    val orderedItems = Array("Steak Sandwich", "Steak Sandwich", "Steak Sandwich", "Steak Sandwich", 
        "Steak Sandwich", "Steak Sandwich", "Steak Sandwich", "Steak Sandwich",
        "Steak Sandwich", "Steak Sandwich", "Steak Sandwich",
        "Steak Sandwich","Steak Sandwich", "Steak Sandwich", "Steak Sandwich", "Steak Sandwich", 
        "Steak Sandwich", "Steak Sandwich", "Steak Sandwich", "Steak Sandwich",
        "Steak Sandwich", "Steak Sandwich", "Steak Sandwich",
        "Steak Sandwich")
    assert(testCafeXMenu.calculateBill(orderedItems) == 128)
  }
}