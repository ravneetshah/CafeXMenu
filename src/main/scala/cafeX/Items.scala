package main.scala.cafeX

class Items (name: String, beverageType: String, itemType: String, price: Double) {

  def getName(): String = {
    name
  }
  
  def getPrice(): Double = {
    price
  }
  
  def getItemType(): String = {
    itemType
  }
  
  def getBeverageType(): String = {
    beverageType
  }
}
