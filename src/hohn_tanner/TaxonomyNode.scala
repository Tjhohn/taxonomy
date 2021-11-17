package hohn_tanner

import scala.collection.mutable.ListBuffer


class TaxonomyNode(val name: String, var features: ListBuffer[String] ) {

  var SubNodes = new ListBuffer[TaxonomyNode]//holds all the lower nodes if exist

  def loadFile() : Unit = {

  }

  def saveFile() : Unit = {

  }

  def find() : Unit = {

  }

  def displayInfo() : String = {
    return name
  }
}
