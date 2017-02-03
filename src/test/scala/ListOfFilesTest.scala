
import org.scalatest.FunSuite

import scala.concurrent.Await

import scala.concurrent.duration._

class ListOfFilesTest extends FunSuite{
  val fileListng=new ListOfFiles
  test("File List Testing"){
    intercept[IllegalArgumentException] {
      assert(Await.result(fileListng.listingFiles("src/resources"), 100.seconds).size == 0)
    }
      assert(Await.result(fileListng.listingFiles("src/main/resources"),100.seconds).size==4)
  }

}
