package hohn_tanner

import hohn_tanner.XMLHelper.makeNode

import scala.collection.mutable
import scala.collection.mutable.ListBuffer
import scala.xml.{Elem, Node, Text}


class TaxonomyNode(private var name: String, private var features: ListBuffer[String] ) {

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

  def writeXml(rank : String): Elem = {
    val attr: mutable.HashMap[String, String] = mutable.HashMap(("name", name))
    var children = Seq[Elem]()
    features.foreach(x => {
      val text = Text(x)
      children = children :+ makeNode("feature", null, text)
    })
    SubNodes.foreach(x => children = children :+ x.writeXml("order"))
    return XMLHelper.makeNode( rank, attr, children )
  }

  def loadXml(node: Node): Unit = {
    val attr: mutable.HashMap[String, String] = mutable.HashMap(("name", name))
    val text = Text(name) //ending node
    return XMLHelper.makeNode( "class", attr, text)
  }
}

object TaxonomyNode {
  def apply(node : Node): TaxonomyNode = {
    var className : String = ""
    val feature : ListBuffer[TaxonomyNode] = ListBuffer[TaxonomyNode]()

    val children = node.child
    for(child <- children) {
      val tag = child.label
      tag match {
        case "class" =>
          className = child.attribute("name").getOrElse("").toString
        case "order" =>
          className = child.attribute("name").getOrElse("").toString
        case "family" =>
          className = child.attribute("name").getOrElse("").toString
        case _ => null
      }
    }
    new TaxonomyNode(className, new ListBuffer[String]  )
  }

  def apply( name: String, features: ListBuffer[String] ): TaxonomyNode ={
    return new TaxonomyNode(name, features)
  }
}
//object PetsFunctional {
//  def apply(): PetsFunctional = {
//    new PetsFunctional(ListBuffer[PetFunctional](), "owner")
//  }
//
//  def apply(node: Node):PetsFunctional = {
//    var owner : String = ""
//    val pets : ListBuffer[PetFunctional] = ListBuffer[PetFunctional]()
//
//    val children = node.child //grab all children
//    for(child <- children) {
//      val tag = child.label
//      println(tag)
//      tag match {
//        case "owner" =>
//          owner = child.attribute("name").getOrElse("").toString
//        case Pet.TAG =>
//          pets += PetFunctional(child)
//        case _ => null
//      }
//    }
//    new PetsFunctional(pets, owner)
//  }
//}
