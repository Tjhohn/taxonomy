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

  def addFamily(node: Node): TaxonomyNode = {
    var newFamily : TaxonomyNode = TaxonomyNode(node.attribute("name").getOrElse("").toString, new ListBuffer[String] )
    val children = node.child
    for(child <- children) {
      val tag = child.label
      tag match {
        case "summary" =>
        //var newOrder : TaxonomyNode = TaxonomyNode(child.attribute("name").getOrElse("").toString, new ListBuffer[String])
        //newClass.addSubNode(newOrder)
        case "feature" =>
          newFamily.addToFeature(child.text)
        case _ => null
      }
    }
    return newFamily
  }

  def addOrder(node: Node): TaxonomyNode = {
    var newOrder : TaxonomyNode = TaxonomyNode(node.attribute("name").getOrElse("").toString, new ListBuffer[String] )
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

  def addClass(node: Node): Unit ={
    var newClass : TaxonomyNode = TaxonomyNode(node.attribute("name").getOrElse("").toString, new ListBuffer[String] )
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
