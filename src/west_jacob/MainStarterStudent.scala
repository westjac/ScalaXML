package west_jacob

import scala.io.StdIn



object MainStarterStudent extends App {
    var choice = -1

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

    //my initializations
    var taxonomy = new Taxonomy()

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
                case 0 => choice = 0
                case 1 => taxonomy.addData()
                case 2 => taxonomy.displayData()
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
}

