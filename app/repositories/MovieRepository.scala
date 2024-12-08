package repositories

import cats.effect.IO
import doobie._
import doobie.implicits._
import models.Movie

class MovieRepository(xa: Transactor[IO]) {

  def findAll: IO[List[Movie]] =
    sql"SELECT id, title, genre, release_year FROM movies".query[Movie].to[List].transact(xa)

}
