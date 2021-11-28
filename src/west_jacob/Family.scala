package west_jacob

import scala.collection.mutable.ListBuffer

class Family() extends TaxNode{
  var summary = new Summary()

  override def loadFile(): Void = ???

  override def saveFile(): Unit = ???

  override def displayInfo(depth: Int): String = {
    var info = ""
    info = info + ("--"*depth) + "Family: " + this.getNodeName() + "\n"
    info = info + ("--"*depth) + "Feature: " + features.mkString("", ", ", "")

    info = info + "\n"
    info = info + ("--"*(depth+1)) + "Genus: 0" + "  Species: 0" + "  Examples: "
    //Recurse down, prob the summary
    //    for (order <- orders) {
    //      info = info + order.displayInfo()
    //    }

    return info
  }

  override def find(): Unit = ???

  def addData(): Unit = {
    print("Add Feature (y/n):> ")
    val input = io.StdIn.readLine()
    var continueFeature: String = input.toLowerCase()
    while(continueFeature != "n")
      {
        print("What Feature:> ")
        val newFeature = io.StdIn.readLine().toLowerCase()
        features.append(newFeature)

        print("\nAdd Feature (y/n):> ")
        val input = io.StdIn.readLine()
        continueFeature = input.toLowerCase()
      }

    //SUMMARY
    print("Add Summary (y/n):> ")
    val addSummary = io.StdIn.readLine().toLowerCase()
    if(addSummary == "y") {

    }

  }

  //Sub class used only by the family class
  class Summary {
    var features = ListBuffer[String]()
  }
}
