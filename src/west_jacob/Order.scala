package west_jacob

import scala.collection.mutable.ListBuffer

class Order() extends TaxNode {
  var families = ListBuffer[Family]()
  override def loadFile(): Void = ???

  override def saveFile(): Unit = ???

  override def displayInfo(depth: Int): String = {
    var info = ""
    info = info + ("--"*depth) + "Order: " + this.getNodeName() + "\n"
    info = info + ("--"*depth) + "Feature: "
    for (feature <- features) {
      info = info + feature + " "
    }
    info = info + "\n"
    //Recurse down
        for (family <- families) {
          info = info + family.displayInfo(depth + 1)
        }

    return info
  }

  override def find(): Unit = ???


  def addData(): Unit = {
    print("What family:> ")
    val input = io.StdIn.readLine()
    val familyName = input.toLowerCase()
    var newFamily = new Family()
    newFamily.setNodeName(familyName)

    var familyFound = false
    for(family <- families) {
      if(family.getNodeName() == familyName) {
        familyFound = true
        newFamily = family
      }
    }

    if(familyFound == false) {
      families.append(newFamily)
      print("Added Family\n")
      print("Continue (y/n):> ")
      var continue = io.StdIn.readLine()
      if (continue == "n")
        return
      else if (continue == "y")
        newFamily.addData()
    }
    else {
      newFamily.addData()
    }
  }
}