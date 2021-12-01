//    SCALA CHECKLIST
//    0. Got it running						          X
//
//    1.	  Add + Display*	34
//    Prompts correct (-1 pt each missed)		X
//    Adds a one of each item and displays 	X
//    Adds multiples 							          X
//    Above displays correctly formatted 		X
//
//
//    2A) Remove + Display*	10
//    Prompts correct							          X
//    Removes and displays correctly 			  X
//
//
//    2B) Add + XML save*	16
//    Console added items saved correctly 		    X
//    Console added multiples is saved correctly 	X
//
//
//    2C) XML load + XML save*	16
//    1 XML file loaded and saved correctly 	      X
//      2+ XML file loaded and saved correctly	    X
//
//
//    2D) XML load + Display*	16
//    1 XML file loaded and displays correctly 	  X
//    2+ XML file loaded and displays correctly	  X
//
//
//    2E) XML+ Display with bad file handing	10
//    Calling display on empty, and after “messy” load works	X
//    Handles files not found correctly						            X
//      Handle “not taxonomy” correctly							          X
//      Handles extra tag, text, and attributes correctly		  X
//      Handles missing attributes correctly					        X
//
//
//    3.	  Stress test for above*	15
//    Loads in file, adds data, and displays/saves correctly		X
//    Appends a file and displays/saves correctly 				      X
//    Removes animal after edits, and displays/saves correctly 	X
//
//
//    4. Find feature*	16
//    CoR format at least there						            X
//    Prompts correct									                X
//    First item found and output formatted correctly	X
//    Handles “not found case”						            X
//
//
//    5.	  Total species count*	12
//    Prompts correct				X
//    Calculated correctly	X
//    Parallelized*				  X
//
//    Every Line with a * has its grading tag: X


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
      while (temp.isEmpty)
        temp = StdIn.readLine()
      choice = temp.toInt

      choice match {
        case 0 => choice = 0
        case 1 => taxonomy.addData()
        case 2 => taxonomy.displayData()
        case 3 => taxonomy.removeClass()
        case 4 => taxonomy.loadXML()
        case 5 => taxonomy.writeXML()
        case 6 => taxonomy.findFeature()
        case 7 => taxonomy.calculateSpecies()
        case _ => println("Invalid option")
      }
    } catch {
      case e: Throwable => print(e)
    }
  }
}

