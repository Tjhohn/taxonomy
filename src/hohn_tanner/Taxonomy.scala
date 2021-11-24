package hohn_tanner

import scala.collection.mutable.ListBuffer
import scala.xml.Node

case class Taxonomy() {

  private var Nodes = new ListBuffer[TaxonomyNode]

  def sumSpecies() : Unit ={

  }

  def accessNodes() : ListBuffer[TaxonomyNode] = {
    Nodes
  }

  def replaceNodes(newList : ListBuffer[TaxonomyNode]) : Unit={
    Nodes = newList
  }

  def addClass(node: Node): Unit ={
    var newClass : TaxonomyNode = TaxonomyNode(node.attribute("name").getOrElse("").toString, new ListBuffer[String] )
    val children = node.child
    for(child <- children) {
      val tag = child.label
      tag match {
        case "order" =>
          var newOrder : TaxonomyNode = TaxonomyNode(child.attribute("name").getOrElse("").toString, new ListBuffer[String])
          newClass.addSubNode(newOrder)
        //if pet tag, make a new pet and have it load the info it wants, then add it to the list
        //val pet = Pet() //full functional would give the node to the constructor
        //pet.loadXml(child)
        //pets += pet
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
