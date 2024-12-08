package service

import cats.effect.IO
import models.Movie
import org.mockito.Mockito._
import org.scalatestplus.mockito.MockitoSugar
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec
import repositories.MovieRepository
import cats.effect.unsafe.implicits.global

class MovieServiceSpec extends AnyWordSpec with Matchers with MockitoSugar {

  "MovieService" should {
    "get all movies" in {
      val mockRepo = mock[MovieRepository]
      val service = new MovieService(mockRepo)

      val movies = List(
        Movie(Some(1), "Inception", "Sci-fi", 2010),
        Movie(Some(2), "The Matrix", "Sci-fi", 1999),
      )

      when(mockRepo.findAll).thenReturn(IO.pure(movies))
      val result = service.getAllMovies.unsafeRunSync()
      result mustBe movies
      verify(mockRepo).findAll
    }
  }

}
