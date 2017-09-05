package main.scala.cafeX

class CafeXMenu {
  var menuList:Array[Items] = new Array[Items](4)
  
  def loadMenu(): Unit= {
    menuList(0) = new Items("Cola", "Cold", "Drink", 0.50F)
    menuList(1) = new Items("Coffee", "Hot", "Drink", 1.00F)
    menuList(2) = new Items("Cheese Sandwich", "Cold", "Food", 2.00F)
    menuList(3) = new Items("Steak Sandwich", "Hot", "Food", 4.50F)
  }
  
  def printMenu() {
    println("CafeXMenu")
    println("===========")
    for(i <- 0 to (menuList.length -1)) {
      var item:Items = menuList(i)
      println(item.getName() + "  " + item.getPrice())
    }
  }
  
  def calculateBill(args: Array[String]): Float = {
    var bill: Float = 0
    if (args.length == 0) {
      throw new IllegalArgumentException("No items ordered")
    }
    for(arg <- 0 to (args.length - 1)) {
      var itemFound: Boolean = false
      for(i <-0 to (menuList.length - 1)) {
        var item:Items = menuList(i)
        if (item.getName() == args(arg)) {
          itemFound = true
          bill += item.getPrice()
        }
      }
      if ( itemFound == false) {
        throw new IllegalArgumentException("Invalid item passed: " + args(arg))
      }
    }
    bill
  }
}

object Main {
  def main(args: Array[String]) {
    val menu = new CafeXMenu()
    menu.loadMenu()
    var bill: Float = 0.0F
    try {
      bill = menu.calculateBill(args)
    } catch {
      case exp:IllegalArgumentException => println(exp.getMessage())
    } finally {
      println("Total Bill: " + bill)
    }
  }
}
