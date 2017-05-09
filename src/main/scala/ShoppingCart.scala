
class ShoppingCart {

  def calculateAppleDiscount(items: Seq[Item]): Double = ???

  def calculateOrangeDiscount(items: Seq[Item]): Double = ???

  def checkout(items: Seq[Item]): Double = items.map(_.price).sum

}
