package west_jacob

import scala.collection.mutable.ListBuffer
import scala.xml.Elem

abstract class TaxNode() {
  private var nodeName = ""
  var features = new ListBuffer[String]()

  def setNodeName(name : String): Unit ={
    nodeName = name
  }

  def getNodeName() : String = nodeName
  def loadFile(): Void
  def saveFile(): Elem
  def displayInfo(depth: Int): String
  def find() : TaxNode
}
