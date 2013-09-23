import com.twitter.algebird._
import com.twitter.scalding._
import com.twitter.scalding.typed.TDsl._

object AggregatorTest {
  val aggregator = new Aggregator[Int, Int, Int] {
    def prepare(v: Int) = v
    def reduce(v1: Int, v2: Int) = Math.min(v1, v2)
    def present(v: Int) = v
  }

  val aggregator2 = aggregator.composePrepare[String](_.toInt)
}


class AggregatorTest(args: Args) extends Job(args) {
  TypedTsv[String](args("input"))
    .groupAll
    .aggregate(AggregatorTest.aggregator2)
    .map(_._2)
    .write(TypedTsv(args("output")))
}
