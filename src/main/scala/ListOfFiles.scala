import java.io.File

import scala.concurrent.Future

import scala.concurrent.ExecutionContext.Implicits.global

class ListOfFiles {
  def listingFiles(directory: String): Future[List[File]] = {
    require(new File(directory).isDirectory)
    val newFile = new File(directory).listFiles
    val dir = newFile.filter(_.isDirectory).toList
    val file = newFile.filter(_.isFile).toList
    makeListOfFiles(dir, file)
  }

  def makeListOfFiles(dir: List[File], listOfFile: List[File]): Future[List[File]] = {
    Future {
      def recursiveCreationOfList(dir: List[File], listOfFile: List[File]): List[File] = {
        dir match {
          case Nil => listOfFile
          case head :: Nil =>
            val d = head.listFiles.filter(_.isDirectory).toList
            val f = head.listFiles.filter(_.isFile).toList
            recursiveCreationOfList(d, listOfFile ::: f)

          case head :: tail =>
            val d = head.listFiles.filter(_.isDirectory).toList
            val f = head.listFiles.filter(_.isFile).toList
            recursiveCreationOfList(d ::: tail, f ::: listOfFile)
        }
      }
      recursiveCreationOfList(dir, listOfFile).reverse
    }
  }

}
