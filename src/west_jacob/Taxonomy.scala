package west_jacob

import java.io.FileNotFoundException
import scala.collection.mutable.ListBuffer
import scala.io
import scala.xml.{Elem, XML}

class Taxonomy {
  var animalClasses = ListBuffer[AnimalClass]()

  def addData(): Unit = {

    print("What class:> ")
    val input = io.StdIn.readLine()
    val taxClass = input.toLowerCase()
    var newClass: AnimalClass = new AnimalClass()
    newClass.setNodeName(taxClass.toString)
    //stuff here to add the class to the tree
    var classFound = false
    for (animalClass <- animalClasses) {
      if (animalClass.getNodeName() == taxClass) {
        classFound = true
        newClass = animalClass
      }
    }
    if (!classFound) {
      animalClasses.append(newClass)
      print("Added Class\n")
      print("Continue (y/n):> ")
      var continue = io.StdIn.readLine()
      if (continue == "n")
        return
      else if (continue == "y")
        newClass.addData()
    }
    else {
      newClass.addData()
    }

  }

  def displayData(): Unit = {
    var data = ""
    for (animalClass <- animalClasses) {
      data = data + animalClass.displayInfo(0)
      data = data + "\n"
    }
    print(data)
  }

  def removeClass(): Unit = {
    print("What class:> ")
    val className = io.StdIn.readLine().toLowerCase()

    var classLoc = -1
    var classFound: Boolean = false
    for (animalClass <- animalClasses) {
      if (animalClass.getNodeName() == className) {
        classFound = true
        classLoc = animalClasses.indexOf(animalClass)
      }
    }
    if (classFound) {
      animalClasses.remove(classLoc)
      print("Removed " + className + "\n")
    }
    else {
      print("Class not found\n")
    }
  }

  def writeXML(): Unit = {
    print("File name:> ")
    val fileName: String = io.StdIn.readLine()

    val xml = animalClasses.map(animalClass => animalClass.saveFile())
    val tree = XMLHelper.makeNode("Taxonomy", null, xml)
    XML.save(fileName, tree, "UTF-8", true, null)

  }

  def loadXML(): Unit = {
    print("File name:> ")
    val fileName = io.StdIn.readLine()

    try {


      val topNode = XML.loadFile(fileName)
      if (topNode.label.toLowerCase() != "taxonomy") {
        println("Invalid XML file. Needs to be an taxonomy XML file")
      }
      else {
        val children = topNode.child
        for (child <- children) {
          var tag = child.label.toLowerCase()
          tag match {
            case "class" =>
              val className = child.attribute("name").getOrElse("").toString
              val animalClass = new AnimalClass()
              animalClass.setNodeName(className)
              animalClass.loadFile(child)
              animalClasses.append(animalClass)
            case _ => null
          }
        }
      }
    } catch {
      case e: FileNotFoundException => println("Could not open file: " + e.getMessage)
    }
  }

  def findFeature(): Unit = {
    print("Feature:> ")
    val featureToFind = io.StdIn.readLine().toLowerCase()

    var tree = ""
    for (animalClass <- animalClasses) {
      tree = tree + animalClass.findFeature(featureToFind)
    }

    if (tree.isEmpty) {
      println(featureToFind + " not found")
    }
    else {
      print(tree)
    }
  }

  def calculateSpecies(): Unit = {
    print("What class:> ")
    val classToCalc = io.StdIn.readLine().toLowerCase()
    var speciesCount = 0
    for (animalClass <- animalClasses) {
      if (animalClass.getNodeName().toLowerCase() == classToCalc) {
        speciesCount = animalClass.getSpeciesCount()
      }
    }

    println("Count: " + speciesCount.toString)
  }
}
