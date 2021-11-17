package hohn_tanner

import scala.collection.mutable.ListBuffer

class Family(name : String, features : ListBuffer[String])
  extends TaxonomyNode( name , features){
  private var species : Int = 0
  private var genus : Int = 0
  private var examples : ListBuffer[String] = new ListBuffer[String]

  def getGenusCount(): Int = {
    genus
  }

  def getSpeciesCount(): Int ={
    species
  }

  def updateGenusCount(num : String): Unit = {
    genus = num.toInt
  }

  def updateSpeciesCount(num : String): Unit = {
    species = num.toInt
  }

  def getExamples(): Unit ={
    examples
  }

  def addToExamples(toAdd : String): Unit = {
    examples += toAdd
  }

}

object Family {
  def apply(name : String, features : ListBuffer[String]) : Family = {
    new Family(name , features)
  }
}