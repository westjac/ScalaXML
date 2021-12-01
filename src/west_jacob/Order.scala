package west_jacob

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.xml.{Elem, Node, Text}

class Order() extends TaxNode {
  var families = ListBuffer[Family]()

  override def loadFile(child: Node): Void = {
    val children = child.child
    for(child <- children) {
      var tag = child.label
      tag match {
        case "family" =>
          val familyName = child.attribute("name").getOrElse("").toString
          val family = new Family()
          family.setNodeName(familyName)
          family.loadFile(child)
          families.append(family)
        case "feature" =>
          val featureName = child.child.mkString("")
          features.append(featureName)
        case _ => null
      }
    }
    return null
  }

  override def saveFile(): Elem = {
    val xml = families.map(family => family.saveFile())
    val nodeName = mutable.HashMap(("Name", this.getNodeName()))

    for(feature <- features) {
      xml.append(XMLHelper.makeNode("Feature", null, Text(feature)))
    }
    XMLHelper.makeNode("Order", nodeName, xml)
  }

  override def displayInfo(depth: Int): String = {
    var info = ""
    info = info + ("--"*depth) + "Order: " + this.getNodeName() + "\n"
    info = info + ("--"*depth) + "Feature: "
    info = info + features.mkString("", ", ", "")

    info = info + "\n"
    //Recurse down
        for (family <- families) {
          info = info + family.displayInfo(depth + 1)
        }

    return info
  }

  override def find(): TaxNode = ???


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
      var continue = io.StdIn.readLine().toLowerCase()
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