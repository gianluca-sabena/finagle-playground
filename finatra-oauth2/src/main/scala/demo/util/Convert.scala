package demo.util

import play.api.libs.json.Json


// scroogle generate class with unaplly method of type def unapply(_item: T): Option[scala.Product6[...]]
// but play json require unaplly function with a tuple

object Convert {
  def fromP2toT2[A,B](p:Product2[A,B]) = (p._1, p._2)
  def fromP4toT4[A,B,C,D](p:Product4[A,B,C,D]) = (p._1, p._2, p._3, p._4)
  def fromP5toT5[A,B,C,D,E](p:Product5[A,B,C,D,E]) = (p._1, p._2, p._3, p._4, p._5)
  def fromP6toT6[A,B,C,D,E,F](p:Product6[A,B,C,D,E,F]) = (p._1, p._2, p._3, p._4, p._5, p._6)
  def fromP7toT7[A,B,C,D,E,F,G](p:Product7[A,B,C,D,E,F,G]) = (p._1, p._2, p._3, p._4, p._5, p._6, p._7)
  def fromP8toT8[A,B,C,D,E,F,G,H](p:Product8[A,B,C,D,E,F,G,H]) = (p._1, p._2, p._3, p._4, p._5, p._6, p._7, p._8)
  def fromP9toT9[A,B,C,D,E,F,G,H,I](p:Product9[A,B,C,D,E,F,G,H,I]) = (p._1, p._2, p._3, p._4, p._5, p._6, p._7, p._8, p._9)
}

//object JsonUtil {
//  def jsonWrapList(tag:String, seq:Seq[String]) = {
//    seq map { m => Json.obj("_url" -> s"/v1/${tag}/${m}", "id" -> m) }
//  }
//}