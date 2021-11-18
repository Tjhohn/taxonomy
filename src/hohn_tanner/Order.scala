package hohn_tanner

import scala.collection.mutable.ListBuffer

class Order(name : String, features : ListBuffer[String])
  extends TaxonomyNode( name , features ){

  override def displayInfo(level : Int ) : String = {
    var outputString = "--"* level + "Order: " + name + "\n"
    outputString = outputString + "--"* level + "feature:"
    if (features.nonEmpty){
      features.foreach(x => outputString = outputString +" "+ x + "," )
      outputString = outputString.substring(outputString.length-2, outputString.length-1) //removes extra ','
    }
    outputString =outputString + "\n"
    this.accessSubNodes().foreach(x => outputString = outputString + x.displayInfo(level+ 1))

    outputString
  }
}
