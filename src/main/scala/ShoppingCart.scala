
class ShoppingCart {

  val APPLE_DISCOUNT_NUMBER = 2D
  val ORANGE_DISCOUNT_NUMBER = 3D

  private def round(num: Double): Double = BigDecimal(num).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble

  def calculateAppleDiscount(items: Seq[Item]): Double =
    round((items.count(_.eq(Apple)) / APPLE_DISCOUNT_NUMBER).toInt * Apple.price)

  def calculateOrangeDiscount(items: Seq[Item]): Double =
    round((items.count(_.eq(Orange)) / ORANGE_DISCOUNT_NUMBER).toInt * Orange.price)

  def checkout(items: Seq[Item]): Double =
    round(items.map(_.price).sum - calculateAppleDiscount(items) - calculateOrangeDiscount(items))

}
