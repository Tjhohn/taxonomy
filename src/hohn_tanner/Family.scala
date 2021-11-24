package hohn_tanner

import hohn_tanner.XMLHelper.makeNode

import scala.:+
import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.xml.{Elem, Text}

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

    outputString = outputString + "\n"
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

  override def writeXml(rank : String): Elem = {
    val attr: mutable.HashMap[String, String] = mutable.HashMap(("name", name))
    var children = Seq[Elem]()
    features.foreach(x => {
      val text = Text(x)
      children = children :+ makeNode("feature", null, text)
    })

    var textString : String = ""
    if (examples.nonEmpty){
      examples.foreach(x => textString = textString +" "+ x + "," )
      textString = textString.substring(1, textString.length) //removes front space
      textString = textString.substring(0, textString.length-1) //removes extra ','
    }
    val text = Text(textString)
    val sumAttr: mutable.HashMap[String, String] = mutable.HashMap(("species", species.toString), ("genus", genus.toString))
    children = children :+ makeNode("summary", sumAttr, text)
    XMLHelper.makeNode( "family", attr, children )
  }


}
