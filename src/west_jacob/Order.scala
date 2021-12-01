package west_jacob

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.xml.{Elem, Node, Text}

class Order() extends TaxNode {
  var families = ListBuffer[Family]()

  override def loadFile(child: Node): Void = {
    val children = child.child
    for(child <- children) {
      var tag = child.label.toLowerCase()
      tag match {
        case "feature" =>
          val featureName = child.child.mkString("")
          features.append(featureName)
        case "family" =>
          val familyName = child.attribute("name").getOrElse("").toString
          val family = new Family()
          family.setNodeName(familyName)
          family.loadFile(child)
          families.append(family)
        case _ => null
      }
    }
    return null
  }

  override def saveFile(): Elem = {
    var xml = ListBuffer[Elem]()

    for(feature <- features) {
      xml.append(XMLHelper.makeNode("Feature", null, Text(feature)))
    }

    val familyXML = families.map(family => family.saveFile())
    val nodeName = mutable.HashMap(("name", this.getNodeName()))

    xml = xml ++ familyXML
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

  override def findFeature(featureToFind: String): String = {
    var tree = ""
    for(feature <- features) {
      if(feature.toLowerCase() == featureToFind) {
        tree = tree + this.displayInfo(0)
        return tree
      }
    }

    //If the feature was not found, move on
    for(family <- families) {
      tree = tree + family.findFeature(featureToFind)
    }

    return tree
  }


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

  override def getSpeciesCount(): Int = {
    val sums = families.par.map(family => {
      family.getSpeciesCount()
    })

    val total = sums.sum
    return total
  }
}