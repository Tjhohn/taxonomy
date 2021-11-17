package hohn_tanner

import scala.collection.mutable.ListBuffer


class TaxonomyNode(val name: String, private var features: ListBuffer[String] ) {

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

  def addToFeature( toAdd : String): Unit ={
    features += toAdd
  }

  def getFeatures(): ListBuffer[String] ={
    features
  }
}
