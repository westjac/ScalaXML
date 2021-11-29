package west_jacob

import scala.collection.mutable.ListBuffer
import scala.io

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
    var data = ""
    for (animalClass <- animalClasses)
    {
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
    for(animalClass <- animalClasses) {
      if (animalClass.getNodeName() == className) {
        classFound = true
        classLoc = animalClasses.indexOf(animalClass)
      }
    }
      if(classFound) {
        animalClasses.remove(classLoc)
        print("Removed " + className + "\n")
      }
      else {
        print("Class not found\n")
      }
  }
}
