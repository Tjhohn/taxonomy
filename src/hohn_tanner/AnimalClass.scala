package hohn_tanner

import scala.collection.mutable.ListBuffer

class AnimalClass(name : String, features : ListBuffer[String])
  extends TaxonomyNode( name , features) {


  override def displayInfo() : String = {
    var outputString = "Class: " + name + "\n"
    return outputString
  }
}
