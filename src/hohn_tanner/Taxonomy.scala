package hohn_tanner

import scala.collection.mutable.ListBuffer
import scala.xml.Node

case class Taxonomy() {

  private var Nodes = new ListBuffer[TaxonomyNode]

  def sumSpecies(className : String) : Int ={
    var Sum : Int = 0
    var option = Nodes.par.find( x => x.getName().toLowerCase() == className.toLowerCase())//GRADING: PARALLEL
    if( option.isDefined){
      option.get.accessSubNodes().foreach(x =>{
        if (x.isInstanceOf[Family]){
          Sum += x.asInstanceOf[Family].getSpeciesCount()
        }
        else {
          x.accessSubNodes().foreach(y => {
            if (y.isInstanceOf[Family]){
              Sum += y.asInstanceOf[Family].getSpeciesCount()
            }
          })
        }
      } )
    }
    return Sum
  }

  def accessNodes() : ListBuffer[TaxonomyNode] = {
    Nodes
  }

  def replaceNodes(newList : ListBuffer[TaxonomyNode]) : Unit={
    Nodes = newList
  }

  private def addFamily(node: Node): TaxonomyNode = {
    var newFamily : Family = new Family(node.attribute("name").getOrElse("").toString, new ListBuffer[String] )
    val children = node.child
    for(child <- children) {
      val tag = child.label
      tag match {
        case "summary" =>
          newFamily.addToExamples(child.text)
          newFamily.updateGenusCount(child.attribute("genus").getOrElse("0").toString)
          newFamily.updateSpeciesCount(child.attribute("species").getOrElse("0").toString)
        case "feature" =>
          newFamily.addToFeature(child.text)
        case _ => null
      }
    }
    return newFamily
  }

  private def addOrder(node: Node): TaxonomyNode = {
    var newOrder : Order = new Order(node.attribute("name").getOrElse("").toString, new ListBuffer[String] )
    val children = node.child
    for(child <- children) {
      val tag = child.label
      tag match {
        case "family" =>
          newOrder.addSubNode(addFamily(child))
        case "feature" =>
          newOrder.addToFeature(child.text)
        case _ => null
      }
    }
    return newOrder
  }

  private def addClass(node: Node): Unit ={
    var newClass : AnimalClass = new AnimalClass(node.attribute("name").getOrElse("").toString, new ListBuffer[String] )
    val children = node.child
    for(child <- children) {
      val tag = child.label
      tag match {
        case "order" =>
          newClass.addSubNode(addOrder(child))
        case "feature" =>
          newClass.addToFeature(child.text)
        case _ => null
      }
    }
    this.accessNodes() += newClass
  }

  def loadXml(node : Node): Unit = {
    /* general pattern
            grab attributes
            foreach childNode
                if the tag is something we care about
                     make child class
                     child.readXML(childNode)
    */
    //would grab attributes here if they were available
    val children = node.child //grab all children
    for(child <- children) {
      val tag = child.label
      tag match {
        case "class" =>
          addClass(child)
        case _ => null
      }
    }
  }

}
