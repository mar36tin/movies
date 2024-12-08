package repositories

import cats.effect.IO
import models.Movie

class MovieRepository {

  def findAll(): IO[List[Movie]] = ???

}
