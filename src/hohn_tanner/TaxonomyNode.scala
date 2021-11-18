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
