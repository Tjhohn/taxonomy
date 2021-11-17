package test

import scala.xml.{Elem, Null, Text, TopScope, UnprefixedAttribute}

object HelloWorld extends App {
  println("Hi!")

  var c =  Elem("prefix", "tag", new UnprefixedAttribute("key","value", Null), TopScope, true, Text("content"))
  println(c)
}