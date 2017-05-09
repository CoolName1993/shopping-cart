import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{MustMatchers, WordSpec}

class ShoppingCartSpec extends WordSpec with MustMatchers with GeneratorDrivenPropertyChecks {

  "checkout" must {

    "calculate the total price of items in a cart" in {
      val input: Seq[Item] = Seq(Apple, Apple, Orange, Apple)
      val classUnderTest: ShoppingCart = new ShoppingCart()
      val result: Double = classUnderTest.checkout(input)
      val expectedResult: Double = 2.05

      result mustBe expectedResult
    }

  }

}
