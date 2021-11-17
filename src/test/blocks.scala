package test

/*
Basic format:
  class <name> (propertyList) [extends className [with classOrTraitlist]]{...}
*/



//basic parent block...just a holder for info right now
//no val or var on the property list? Defaults to private...
class InheritanceBlocks(width: Int, height: Int, depth: Int) {

  //PUBLIC by default (not package-private)
  def myPrint(): Unit = println(s"Width X Height X Depth: $width x $height x $depth")
}

//basic block with up to 3 lengths
object InheritanceBlocks {
  def apply(): InheritanceBlocks = {
    new InheritanceBlocks(1, 1, 1)
  }

  def apply(width: Int): InheritanceBlocks = {
    new InheritanceBlocks(width, width, width)
  }

  def apply(width: Int, height: Int, depth: Int): InheritanceBlocks = {
    new InheritanceBlocks(width, height, depth)
  }
}


/*
basic inheritance-----------------------------------------------------

First weird issue:
  We must copy(-paste!) the parent's (propertyList) and then add our own.
  Name must match...


Why? This is a functional languages, which doesn't like side effects.
there were massive issues with accidental use of empty constructors in Java/C++

*/


class ColorBlock(color: String, width: Int, height: Int, depth: Int)
  extends InheritanceBlocks(width: Int, height: Int, depth: Int) {

  //REQUIRES override..it will not compile otherwise
  override def myPrint(): Unit = {
    println(s"Color: $color")
    super.myPrint() //call the parent with super like in Java
  }
}

object ColorBlock {
  def apply(color: String): ColorBlock = {
    new ColorBlock(color, 1, 1, 1)
  }

  def apply(color: String, width: Int): ColorBlock = {
    new ColorBlock(color, width, width, width)
  }

  def apply(color: String, width: Int, height: Int, depth: Int): ColorBlock = {
    new ColorBlock(color, width, height, depth)
  }
}
/*
QUESITION:
The "copy the propertyList" require gets nuts pretty fast


How do we solve this?
  A: Just deal with it
  B: Try to flatten your hierarchy
  C: Default parameters
*/





//ANSWER: C: defaults
class RedWeightedColorBlock(weight: Double, color: String = "Red", width: Int = 1, height: Int = 1, depth: Int = 1)
  extends ColorBlock(color: String, width: Int, height: Int, depth: Int) {

  override def myPrint(): Unit = {
    print(s"Weight: $weight ")
    super.myPrint()
  }
}

object RedWeightedColorBlock {
  def apply(weight: Double) : RedWeightedColorBlock = {
    new RedWeightedColorBlock(weight)
  }
}


//after applying the default...our list size goes back to normal
class DefaultBlock(weight: Double = 1) extends RedWeightedColorBlock(weight) {}

object DefaultBlock {
  def apply(): DefaultBlock = {
    new DefaultBlock()
  }
}

/*
OK, there is one more option: do not use the class paramList.

In this case, you can just use the constructor in object like normal
 */


object TestBlock extends App {
  val a = InheritanceBlocks()
  val b = InheritanceBlocks(2)
  val c = InheritanceBlocks(1, 2, 3)

  a.myPrint()
  b.myPrint()
  c.myPrint()

  val d = ColorBlock("Blue")
  val e = ColorBlock("Green", 3)
  val f = ColorBlock("Red", 5, 2, 3)

  d.myPrint()
  e.myPrint()
  f.myPrint()

  val h = RedWeightedColorBlock(2)
  h.myPrint()


  val i = DefaultBlock()
  i.myPrint()
}