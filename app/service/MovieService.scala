package service

import cats.effect.IO
import models.Movie
import repositories.MovieRepository

class MovieService(movieRepository: MovieRepository)  {

  def getAllMovies: IO[List[Movie]] = movieRepository.findAll

}
