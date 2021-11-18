package hohn_tanner

import scala.collection.mutable.ListBuffer


class TaxonomyNode(private val name: String, private var features: ListBuffer[String] ) {

  private var SubNodes = new ListBuffer[TaxonomyNode]//holds all the lower nodes if exist

  def getName(): String ={
    name
  }

  def accessSubNodes() : ListBuffer[TaxonomyNode] ={
    SubNodes
  }

  def addSubNode(toAdd : TaxonomyNode) : Unit ={
    SubNodes += toAdd
  }

  def loadFile() : Unit = {

  }

  def saveFile() : Unit = {

  }

  def find() : Unit = {

  }

  def displayInfo(level : Int) : String = {
    name
  }

  def addToFeature( toAdd : String): Unit ={
    features += toAdd
  }

  def getFeatures(): ListBuffer[String] ={
    features
  }
}
