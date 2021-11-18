package hohn_tanner

import scala.collection.mutable.ListBuffer

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


}
