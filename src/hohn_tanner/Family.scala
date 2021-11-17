package hohn_tanner

import scala.collection.mutable.ListBuffer

class Family( var species : Int = 0, var genus : Int = 0,var examples : ListBuffer[String] = new ListBuffer[String],
              name : String, features : ListBuffer[String])
  extends TaxonomyNode( name , features){


}
