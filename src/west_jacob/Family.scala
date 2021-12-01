package west_jacob

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.xml.{Elem, Node, NodeSeq, Text}

class Family() extends TaxNode{
  var summary = new Summary()

  override def loadFile(child: Node): Void = {
    val children = child.child
    for(child <- children) {
      var tag = child.label
      tag match {
        case "feature" =>
          val featureName = child.child.mkString("")
          features.append(featureName)
        case "summary" =>
          summary.genusCount = child.attribute("genus").getOrElse("").toString.toInt
          summary.speciesCount = child.attribute("species").getOrElse("").toString.toInt
          summary.examples = child.child.mkString("").split(',').map(_.trim).to[ListBuffer]
        case _ => null
      }
    }
    return null
  }

  override def saveFile(): Elem = {
    var xmlNodes = ListBuffer[Elem]()
    val nodeName = mutable.HashMap(("Name", this.getNodeName()))

    for(feature <- features) {
      xmlNodes.append(XMLHelper.makeNode("Feature", null, Text(feature)))
    }

    val xmlSummary = mutable.HashMap(("Genus", summary.genusCount.toString), ("Species", summary.speciesCount.toString))
    xmlNodes.append(XMLHelper.makeNode("Summary", xmlSummary, Text(summary.examples.mkString("", ", ", ""))))

    XMLHelper.makeNode("Family", nodeName, xmlNodes)
  }

  override def displayInfo(depth: Int): String = {
    var info = ""
    info = info + ("--"*depth) + "Family: " + this.getNodeName() + "\n"
    info = info + ("--"*depth) + "Feature: " + features.mkString("", ", ", "")

    info = info + "\n"
    info = info + ("--"*(depth+1)) + "Genus: " + summary.genusCount.toString + "  Species: " + summary.speciesCount.toString + "  Examples: "

    //Print the summary
    info = info + summary.examples.mkString("", ", ", "") + "\n"

    return info
  }

  override def find(): TaxNode = ???

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
      print("Update genus count (" + summary.genusCount.toString + "):> ")
      summary.genusCount = io.StdIn.readInt()

      print("Update species count (" + summary.speciesCount.toString + "):> ")
      summary.speciesCount = io.StdIn.readInt()

      print("Add example (y/n):> ")
      var addExample = io.StdIn.readLine()

      while(addExample == "y") {
        print("What example:> ")
        val example = io.StdIn.readLine()
        summary.examples.append(example)

        print("Add example (y/n):> ")
        addExample = io.StdIn.readLine()
      }


    }

  }

  //Sub class used only by the family class
  class Summary {
    var genusCount: Int = 0;
    var speciesCount: Int = 0;
    var examples = ListBuffer[String]()
  }
}
