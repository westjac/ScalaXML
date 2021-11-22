package west_jacob

import scala.collection.mutable.ListBuffer

class Taxonomy {
  var animalClasses = ListBuffer[AnimalClass]()

  def addData(): Unit = {

    print("What class:> ")
    var taxClass = io.StdIn.readLine()
    var newClass = new AnimalClass()
    newClass.setNodeName(taxClass)
    //stuff here to add the class to the tree
    var classFound = false
    for(animalClass <- animalClasses) {
      if(animalClass.getNodeName() == taxClass) {
        classFound = true
        newClass = animalClass
      }
    }
    if(!classFound) {
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
    print("Class: mammalia\n")
    print("Feature: ")
  }
}
