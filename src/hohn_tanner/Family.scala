package hohn_tanner

import scala.collection.mutable.ListBuffer

class Family(name : String, features : ListBuffer[String])
  extends TaxonomyNode( name , features){
  private var species : Int = 0
  private var genus : Int = 0
  private var examples : ListBuffer[String] = new ListBuffer[String]

  override def displayInfo(level : Int) : String = {
    var outputString = "--"* level + "Family: " + name + "\n"
    outputString = outputString + "--"* level + "feature:"
    if (features.nonEmpty){
      features.foreach(x => outputString = outputString +" "+ x + "," )
      outputString = outputString.substring(0, outputString.length-1) //removes extra ','
    }
    outputString =outputString + "\n"
    this.accessSubNodes().foreach(x => outputString = outputString + x.displayInfo(level+ 1))
    outputString = outputString + "\n"
    outputString = outputString + "--"* (level + 1) + "Genus: " + genus.toString + "  Species: " + species.toString + "  Examples:"
    if (examples.nonEmpty){
      examples.foreach(x => outputString = outputString +" "+ x + "," )
      outputString = outputString.substring(0, outputString.length-1) //removes extra ','
    }

    outputString
  }

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
