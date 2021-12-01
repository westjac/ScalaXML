package west_jacob

import scala.collection.mutable.ListBuffer
import scala.xml.{Elem, Node}

abstract class TaxNode() {
  private var nodeName = ""
  var features = new ListBuffer[String]()

  def setNodeName(name : String): Unit ={
    nodeName = name
  }

  def getNodeName() : String = nodeName
  def loadFile(child : Node): Void
  def saveFile(): Elem
  def displayInfo(depth: Int): String
  def findFeature(featureToFind: String) : String
  def getSpeciesCount(): Int
}
