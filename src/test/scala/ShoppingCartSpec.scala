import org.scalacheck.{Arbitrary, Gen}
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{MustMatchers, WordSpec}

class ShoppingCartSpec extends WordSpec with MustMatchers with GeneratorDrivenPropertyChecks {

  def checkoutGen: Gen[Seq[Item]] =
  for {
    size <- Gen.chooseNum(0, 10)
    items <- Gen.listOfN(size, Gen.oneOf(Apple, Orange))
  } yield items

  implicit val arbCheckoutGen: Arbitrary[Seq[Item]] = Arbitrary(checkoutGen)

  "checkout" must {

    "calculate the total price of 3 Apples and 1 Orange to 2.05" in {
      val classUnderTest: ShoppingCart = new ShoppingCart()
      val input = Seq(Apple, Apple, Apple, Orange)
      val result: Double = classUnderTest.checkout(input)
      val expectedResult: Double = 2.05D

      result mustBe expectedResult
    }

    "calculate the total price of 1 Apple and 3 Oranges to 1.35" in {
      val classUnderTest: ShoppingCart = new ShoppingCart()
      val input = Seq(Apple, Orange, Orange, Orange)
      val result: Double = classUnderTest.checkout(input)
      val expectedResult: Double = 0.6D + 0.25D + 0.25D + 0.25D

      result mustBe expectedResult
    }


    "calculate the total price of items in a randomly generated cart" in {
      forAll() { input: Seq[Item] =>
        val classUnderTest: ShoppingCart = new ShoppingCart()
        val result: Double = classUnderTest.checkout(input)
        val expectedResult: Double = input.foldLeft(0D)((total, item) => total + item.price)

        result mustBe expectedResult
      }
    }

  }

}
