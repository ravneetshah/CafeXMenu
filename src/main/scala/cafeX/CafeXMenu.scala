package main.scala.cafeX

class CafeXMenu {
  var menuList:Array[Items] = new Array[Items](4)
  
  def loadMenu(): Unit= {
    menuList(0) = new Items("Cola", "Cold", "Drink", 0.50)
    menuList(1) = new Items("Coffee", "Hot", "Drink", 1.00)
    menuList(2) = new Items("Cheese Sandwich", "Cold", "Food", 2.00)
    menuList(3) = new Items("Steak Sandwich", "Hot", "Food", 4.50)
  }
  
  def printMenu() {
    println("CafeXMenu")
    println("===========")
    for(i <- 0 to (menuList.length -1)) {
      var item:Items = menuList(i)
      println(item.getName() + "  " + item.getPrice())
    }
  }
  
  def calculateServiceCharge(bill: Double, onlyDrinks: Boolean, hotBeverage: Boolean): Double = {
    var serviceCharge: Double = 0.0
    if (onlyDrinks == true) {
      serviceCharge = 0.0
    } else if (hotBeverage == true) {
      serviceCharge = (20*bill)/100
      
      if (serviceCharge > 20 ) {
        serviceCharge = 20.0
      }
    } else {
      serviceCharge = (10*bill)/100
    }
    
    BigDecimal(serviceCharge).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
  }
  
  def calculateBill(args: Array[String]): Double = {
    var bill: Double = 0.0
    if (args.length == 0) {
      throw new IllegalArgumentException("No items ordered")
    }
    var onlyDrinks: Boolean = true
    var hotBeverage: Boolean = false
    
    for(arg <- 0 to (args.length - 1)) {
      var itemFound: Boolean = false
      
      for(i <-0 to (menuList.length - 1)) {
        var item:Items = menuList(i)
        if (item.getName() == args(arg)) {
          itemFound = true
          bill += item.getPrice()
          if (item.getItemType() != "Drink") {
            onlyDrinks = false
          }
          if (item.getBeverageType() == "Hot") {
            hotBeverage = true
          }
        }
      }
      if ( itemFound == false) {
        throw new IllegalArgumentException("Invalid item passed: " + args(arg))
      }
    }
    var serviceCharge: Double = calculateServiceCharge(bill, onlyDrinks, hotBeverage) 
    bill = bill + serviceCharge
    bill
  }
}

object Main {
  def main(args: Array[String]) {
    val menu = new CafeXMenu()
    menu.loadMenu()
    var bill: Double = 0.0
    try {
      bill = menu.calculateBill(args)
    } catch {
      case exp:IllegalArgumentException => println(exp.getMessage())
    } finally {
      println("Total Bill: " + bill)
    }
  }
}
