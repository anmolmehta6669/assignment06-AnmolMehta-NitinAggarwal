
import org.scalatest.FunSuite

import scala.concurrent.Await

import scala.concurrent.duration._

class ListOfFilesTest extends FunSuite {
  val fileListng = new ListOfFiles
  test("Path Error") {
    intercept[IllegalArgumentException] {
      assert(Await.result(fileListng.listingFiles("src/resources"), 100.seconds).size == 0)
    }
  }
  test("Only SubFolders Directory") {
    assert(Await.result(fileListng.listingFiles("src/main/resources"), 100.seconds).size == 4)
  }
  test("Nil Directory") {
    assert(Await.result(fileListng.listingFiles("src/main/resources/Directory2"), 100.seconds).size == 0)
  }
  test("Only Files Directory") {
    assert(Await.result(fileListng.listingFiles("src/main/resources/Directory3/Directory4"), 100.seconds).size == 1)
  }
  test("Directory with subFolders and files") {
    assert(Await.result(fileListng.listingFiles("src/main/resources/Directory3"), 100.seconds).size == 2)
  }

}
