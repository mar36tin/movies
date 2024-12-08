package models

import play.api.libs.json._

case class Movie(id: Option[Int], title: String, genre: String, releaseYear: Int)

object Movie {
  implicit val movieFormat: OFormat[Movie] = Json.format[Movie]
}
