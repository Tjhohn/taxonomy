package hohn_tanner

import scala.collection.mutable.ListBuffer

case class Taxonomy() {

  private var Nodes = new ListBuffer[TaxonomyNode]

  def sumSpecies() : Unit ={

  }

  def accessNode() : ListBuffer[TaxonomyNode] = {
    Nodes
  }


}
