package hohn_tanner

import scala.io.StdIn
import hohn_tanner.Taxonomy

import scala.collection.mutable.ListBuffer



object MainStarterStudent extends App {
    var choice = -1
    var taxonomy = Taxonomy()
    val menu: String =
        """
          |1) Add data
          |2) Display data
          |3) Remove class
          |4) Load XML
          |5) Write XML
          |6) Find feature
          |7) Calculate species
          |0) Quit
          |Choice:> """.stripMargin

    var temp = ""

    while (choice != 0) {
        try {
            print(menu)
            //something to strip out empty lines
            temp = StdIn.readLine()
            while(temp.isEmpty)
                temp = StdIn.readLine()
            choice = temp.toInt

            choice match {
                case 0 => println("")
                case 1 => addData(taxonomy)
                case 2 => taxonomy.accessNode().foreach( x =>  println( x.displayInfo() )  )
                case 3 => println("TODO")
                case 4 => println("TODO")
                case 5 => println("TODO")
                case 6 => println("TODO")
                case 7 => println("TODO")
                case _ => println("Invalid option")
            }
        } catch {
            case e: Throwable => print(e)
        }
    }

    def addData(tree : Taxonomy ): Unit = {
        var classNode :  TaxonomyNode = null
        var continue = "n"

        print("What class:> ")
        val className = StdIn.readLine()
        var classOption = tree.accessNode().find(node => node.name == className.toLowerCase())
        if(classOption.isDefined){
            classNode = classOption.get
            continue = "y"
        }
        else {
            classNode = new AnimalClass(className.toLowerCase(), new ListBuffer[String])
            tree.accessNode() += classNode
            print("Added class\n")
            print("Continue (y/n):> ")
            continue = StdIn.readLine()
        }//order
        var orderNode : TaxonomyNode = null
        if (continue.toLowerCase() == "y"){

            print("What order:> ")
            val orderName = StdIn.readLine()
            var orderOption = classNode.SubNodes.find(node => node.name == orderName.toLowerCase())
            if(orderOption.isDefined){
                orderNode = orderOption.get
            }
            else{
                orderNode = new Order(orderName.toLowerCase(), new ListBuffer[String])
                classNode.SubNodes += orderNode
                print("Added order\n")
                print("Continue (y/n):> ")
                continue = StdIn.readLine()
            }//family
            var familyNode : TaxonomyNode = null
            if (continue.toLowerCase() == "y"){
                print("What family:> ")
                val familyName = StdIn.readLine()
                var familyOption = orderNode.SubNodes.find(node => node.name == familyName.toLowerCase())
                if(familyOption.isDefined){
                    familyNode = familyOption.get
                }
                else{
                    familyNode = new Family(0, 0, name = familyName.toLowerCase(), features = new ListBuffer[String])
                    orderNode.SubNodes += familyNode
                    print("Added family\n")
                    print("Continue (y/n):> ")
                    continue = StdIn.readLine()
                }//summary
                if (continue.toLowerCase() == "y"){
                    print("Add summary (y/n):> ")
                    continue = StdIn.readLine()
                    if( continue.toLowerCase() == "y"){
                        print(s"Update genus count (${familyNode}):> ")
                        val genus = StdIn.readLine()
                        //familyNode.genus = genus
                        print(s"Update species count (${familyNode}):> ")
                        val species = StdIn.readLine()
                        //familyNode.species = species
                    }
                }
            }
        }



    }


}

