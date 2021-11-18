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

    var tempInput = ""

    while (choice != 0) {
        try {
            print(menu)
            //something to strip out empty lines
            tempInput = StdIn.readLine()
            while(tempInput.isEmpty)
                tempInput = StdIn.readLine()
            choice = tempInput.toInt

            choice match {
                case 0 => println("")
                case 1 => addData(taxonomy)//GRADING: ADD
                case 2 => taxonomy.accessNodes().foreach(x =>  println( x.displayInfo(0)))//GRADING: PRINT
                case 3 => removeAnimalClass(taxonomy)
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
        var classOption = tree.accessNodes().find(node => node.getName() == className.toLowerCase())
        if(classOption.isDefined){
            classNode = classOption.get
            continue = "y"
        }
        else {
            classNode = new AnimalClass(className.toLowerCase(), new ListBuffer[String])
            tree.accessNodes() += classNode
            print("Added class\n")
            print("Continue (y/n):> ")
            continue = StdIn.readLine()
        }//order
        var orderNode : TaxonomyNode = null
        if (continue.toLowerCase() == "y"){
            print("What order:> ")
            val orderName = StdIn.readLine()
            var orderOption = classNode.accessSubNodes().find(node => node.getName() == orderName.toLowerCase())
            if(orderOption.isDefined){
                orderNode = orderOption.get
            }
            else{
                orderNode = new Order(orderName.toLowerCase(), new ListBuffer[String])
                classNode.addSubNode(orderNode)
                print("Added order\n")
                print("Continue (y/n):> ")
                continue = StdIn.readLine()
            }//family
            var familyNode : TaxonomyNode = null
            if (continue.toLowerCase() == "y"){
                print("What family:> ")
                val familyName = StdIn.readLine()
                var familyOption = orderNode.accessSubNodes().find(node => node.getName() == familyName.toLowerCase())
                if(familyOption.isDefined){
                    familyNode = familyOption.get
                }
                else{
                    familyNode = new Family( familyName.toLowerCase(), new ListBuffer[String])
                    orderNode.addSubNode(familyNode)
                    print("Added family\n")
                    print("Continue (y/n):> ")
                    continue = StdIn.readLine()
                }
                if(continue.toLowerCase() == "y"){
                    var tempFamily = familyNode.asInstanceOf[Family]
                    continue = "n"
                    do {
                        print("Add feature (y/n):> ")
                        continue = StdIn.readLine()
                        if(continue.toLowerCase() == "y"){
                            print("\nWhat feature:> ")
                            var feature =StdIn.readLine().toLowerCase()
                            tempFamily.addToFeature(feature)
                        }
                    }while(continue.toLowerCase() == "y")
                    print("Add summary (y/n):> ")//summary
                    continue = StdIn.readLine()
                    if( continue.toLowerCase() == "y"){
                        print(s"Update genus count (${tempFamily.getGenusCount()}):> ")
                        val genus = StdIn.readLine()
                        tempFamily.updateGenusCount(genus)
                        print(s"Update species count (${tempFamily.getSpeciesCount()}):> ")
                        val species = StdIn.readLine()
                        tempFamily.updateSpeciesCount(species)
                        do {
                            print("Add example (y/n):> ")
                            continue = StdIn.readLine()
                            if(continue.toLowerCase() == "y"){
                                print("What example:> ")
                                var example =StdIn.readLine().toLowerCase()
                                tempFamily.addToExamples(example)
                            }
                        }while(continue.toLowerCase() == "y")
                    }
                }
            }
        }
    }

    def removeAnimalClass(tree : Taxonomy ): Unit ={
        var classNode :  TaxonomyNode = null
        print("What class:> ")
        val className = StdIn.readLine()
        var classOption = tree.accessNodes().find(node => node.getName() == className.toLowerCase())
        if(classOption.isDefined){
            classNode = classOption.get
            var temp = tree.accessNodes()
            tree.replaceNodes(temp.filter(_ != classNode))
            println(s"Removed ${classNode.getName()}")
        }
        else {
            println("Class not found")
        }
    }

}

