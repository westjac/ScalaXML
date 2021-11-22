package west_jacob

import scala.collection.mutable.ListBuffer

class AnimalClass extends TaxNode {
  var orders = ListBuffer[Order]()

  override def loadFile(): Void = ???

  override def saveFile(): Unit = ???

  override def displayInfo(): String = ???

  override def find(): Unit = ???

  def addData(): Unit = {
    print("What Order:> ")
    var orderName = io.StdIn.readLine()
    var newOrder = new Order()
    newOrder.setNodeName(orderName)

    var orderFound = false
    for(order <- orders) {
      if(order.getNodeName() == orderName) {
        orderFound = true
        newOrder = order
      }
    }
    if(!orderFound) {
      orders.append(newOrder)
      print("Added Order\n")
      print("Continue (y/n):> ")
      var continue = io.StdIn.readLine()
      if (continue == "n")
        return
      else if (continue == "y")
        return
        //newClass.addData()
    }
    else {
     // newClass.addData()
    }
  }

  def displayData(): Unit = {
    print("--Order: carnivora\n")
    print("--Feature: ")
  }
}
