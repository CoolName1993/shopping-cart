
class ShoppingCart {

  def checkout(items: Seq[Item]): Double = items.foldLeft(0D)((total, item) => total + item.price)

}
