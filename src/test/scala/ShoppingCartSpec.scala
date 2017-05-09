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

  "calculateAppleDiscount" must {

    "calculate the total discount to be 0 when 0 apples are in the basket" in {
      val classUnderTest: ShoppingCart = new ShoppingCart()
      val input: Seq[Item] = Seq()
      val result: Double = classUnderTest.calculateAppleDiscount(input)
      val expectedResult: Double = 0D

      result mustBe expectedResult
    }

    "calculate the total discount to be 0 when 1 apple is in the basket" in {
      val classUnderTest: ShoppingCart = new ShoppingCart()
      val input: Seq[Item] = Seq(Apple)
      val result: Double = classUnderTest.calculateAppleDiscount(input)
      val expectedResult: Double = 0D

      result mustBe expectedResult
    }

    "calculate the total discount to be 0.6 when 2 apples are in the basket" in {
      val classUnderTest: ShoppingCart = new ShoppingCart()
      val input: Seq[Item] = Seq(Apple, Apple)
      val result: Double = classUnderTest.calculateAppleDiscount(input)
      val expectedResult: Double = 0.6D

      result mustBe expectedResult
    }

    "calculate the total discount to be 1.8 when 6 apples are in the basket" in {
      val classUnderTest: ShoppingCart = new ShoppingCart()
      val input: Seq[Item] = Seq(Apple, Apple, Apple, Apple, Apple, Apple)
      val result: Double = classUnderTest.calculateAppleDiscount(input)
      val expectedResult: Double = 1.8D

      result mustBe expectedResult
    }

    "calculate the total discount to be 1.8 when 7 apples are in the basket" in {
      val classUnderTest: ShoppingCart = new ShoppingCart()
      val input: Seq[Item] = Seq(Apple, Apple, Apple, Apple, Apple, Apple, Apple)
      val result: Double = classUnderTest.calculateAppleDiscount(input)
      val expectedResult: Double = 1.8D

      result mustBe expectedResult
    }

    "calculate the total discount to be 1.2 when 5 apples and 2 oranges are in the basket" in {
      val classUnderTest: ShoppingCart = new ShoppingCart()
      val input: Seq[Item] = Seq(Apple, Apple, Apple, Apple, Apple, Orange, Orange)
      val result: Double = classUnderTest.calculateAppleDiscount(input)
      val expectedResult: Double = 1.2D

      result mustBe expectedResult
    }

  }

  "calculateOrangeDiscount" must {

    "calculate the total discount to be 0 when 0 oranges are in the basket" in {
      val classUnderTest: ShoppingCart = new ShoppingCart()
      val input: Seq[Item] = Seq()
      val result: Double = classUnderTest.calculateOrangeDiscount(input)
      val expectedResult: Double = 0D

      result mustBe expectedResult
    }

    "calculate the total discount to be 0 when 2 oranges are in the basket" in {
      val classUnderTest: ShoppingCart = new ShoppingCart()
      val input: Seq[Item] = Seq(Orange, Orange)
      val result: Double = classUnderTest.calculateOrangeDiscount(input)
      val expectedResult: Double = 0D

      result mustBe expectedResult
    }

    "calculate the total discount to be 0.25 when 3 oranges are in the basket" in {
      val classUnderTest: ShoppingCart = new ShoppingCart()
      val input: Seq[Item] = Seq(Orange, Orange, Orange)
      val result: Double = classUnderTest.calculateOrangeDiscount(input)
      val expectedResult: Double = 0.25D

      result mustBe expectedResult
    }

    "calculate the total discount to be 0.5 when 6 oranges are in the basket" in {
      val classUnderTest: ShoppingCart = new ShoppingCart()
      val input: Seq[Item] = Seq(Orange, Orange, Orange, Orange, Orange, Orange)
      val result: Double = classUnderTest.calculateOrangeDiscount(input)
      val expectedResult: Double = 0.5D

      result mustBe expectedResult
    }

    "calculate the total discount to be 0.5 when 7 oranges are in the basket" in {
      val classUnderTest: ShoppingCart = new ShoppingCart()
      val input: Seq[Item] = Seq(Orange, Orange, Orange, Orange, Orange, Orange)
      val result: Double = classUnderTest.calculateOrangeDiscount(input)
      val expectedResult: Double = 0.5D

      result mustBe expectedResult
    }

    "calculate the total discount to be 0.5 when 7 oranges and 3 apples are in the basket" in {
      val classUnderTest: ShoppingCart = new ShoppingCart()
      val input: Seq[Item] = Seq(Orange, Orange, Orange, Orange, Orange, Orange, Apple, Apple, Apple)
      val result: Double = classUnderTest.calculateOrangeDiscount(input)
      val expectedResult: Double = 0.5D

      result mustBe expectedResult
    }

  }

  "checkout" must {

    "calculate the total price of 3 Apples and 1 Orange to 1.45" in {
      val classUnderTest: ShoppingCart = new ShoppingCart()
      val input: Seq[Item] = Seq(Apple, Apple, Apple, Orange)
      val result: Double = classUnderTest.checkout(input)
      val expectedResult: Double = 1.45D

      result mustBe expectedResult
    }

    "calculate the total price of 1 Apple and 3 Oranges to 1.10" in {
      val classUnderTest: ShoppingCart = new ShoppingCart()
      val input: Seq[Item] = Seq(Apple, Orange, Orange, Orange)
      val result: Double = classUnderTest.checkout(input)
      val expectedResult: Double = 1.10D

      result mustBe expectedResult
    }


    "calculate the total price of items in a randomly generated cart" in {
      forAll() { input: Seq[Item] =>
        val classUnderTest: ShoppingCart = new ShoppingCart()
        val result: Double = classUnderTest.checkout(input)
        val appleDiscount: Double = classUnderTest.calculateAppleDiscount(input)
        val orangeDiscount: Double = classUnderTest.calculateOrangeDiscount(input)
        val total = input.foldLeft(0D)((total, item) => total + item.price) - appleDiscount - orangeDiscount
        val expectedResult: Double = BigDecimal(total).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble

        result mustBe expectedResult
      }
    }

  }

}
