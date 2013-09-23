import com.twitter.scalding._
import com.twitter.scalding.TDsl._

import org.specs2._
import org.specs2.matcher.ThrownExpectations


class AggregatorTestSpec extends Specification with ThrownExpectations { def is = s2"""
AggregatorTest works $test
"""

  def test = {
    JobTest("AggregatorTest")
      .arg("input", "fakeInput")
      .arg("output", "fakeOutput")
      .source(TypedTsv[String]("fakeInput"), List("1", "2", "3"))
      .sink[Int](TypedTsv[Int]("fakeOutput"))(x => x.head === 1)
      .run.finish

    ok
  }
}
