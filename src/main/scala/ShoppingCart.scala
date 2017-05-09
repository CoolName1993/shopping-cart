
class ShoppingCart {

  def checkout(items: Seq[Item]): Double = items.map(_.price).sum

}
