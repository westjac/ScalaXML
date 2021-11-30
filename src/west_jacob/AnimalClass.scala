package west_jacob

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.xml.Elem

class AnimalClass() extends TaxNode {
  var orders = ListBuffer[Order]()

  override def loadFile(): Void = ???

  override def saveFile(): Elem = {
    val xml = orders.map(order => order.saveFile())
    val nodeName = mutable.HashMap(("Name", this.getNodeName()))
    XMLHelper.makeNode("Class", nodeName, xml)
  }

  override def find(): TaxNode = ???

  def addData(): Unit = {
    print("What Order:> ")
    val input = io.StdIn.readLine()
    val orderName = input.toLowerCase()
    var newOrder = new Order()
    newOrder.setNodeName(orderName)

    var orderFound = false
    for(order <- orders) {
      if(order.getNodeName() == orderName) {
        orderFound = true
        newOrder = order
      }
    }
    if(orderFound == false) {
      orders.append(newOrder)
      print("Added Order\n")
      print("Continue (y/n):> ")
      var continue = io.StdIn.readLine()
      if (continue == "n")
        return
      else if (continue == "y")
        newOrder.addData()
    }
    else {
     newOrder.addData()
    }
  }

  override def displayInfo(depth: Int): String = {
    var info = ""
    info = info + "Class: " + this.getNodeName() +"\n"
    info = info + "Feature: "
    for ( feature <- features) {
      info = info + feature + " "
    }
    info = info + "\n"

    //Recurse down
    for (order <- orders) {
      info = info + order.displayInfo(depth + 1)
    }
    return info
  }
}
