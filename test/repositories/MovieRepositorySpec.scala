package repositories

import cats.effect.{IO, Resource}
import doobie.hikari.HikariTransactor
import doobie.implicits._
import models.Movie
import org.scalatest.matchers.must.Matchers
import org.scalatest.wordspec.AnyWordSpec
import cats.effect.unsafe.implicits.global
import cats.implicits.toTraverseOps

import scala.concurrent.ExecutionContext

class MovieRepositorySpec extends AnyWordSpec with Matchers {

  // Setup H2 in-memory database using HikariTransactor
  def transactor: Resource[IO, HikariTransactor[IO]] = {
    HikariTransactor.newHikariTransactor[IO](
      driverClassName = "org.h2.Driver",
      url = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", // In-memory database
      user = "",
      pass = "",
      connectEC = ExecutionContext.global
    )
  }

  val setupQuery: doobie.Update0 = sql"""
    CREATE TABLE movies (
      id SERIAL PRIMARY KEY,
      title VARCHAR NOT NULL,
      genre VARCHAR NOT NULL,
      release_year INT NOT NULL
    )
  """.update

  "MovieRepository" should {

    "retrieve all movies" in {
      transactor.use { xa =>
        val repo = new MovieRepository(xa)

        val movies = List(
          Movie(None, "Inception", "Sci-Fi", 2010),
          Movie(None, "The Matrix", "Sci-Fi", 1999)
        )

        val ioTest = for {
          // Set up schema
          _ <- setupQuery.run.transact(xa)
          // Insert movies into the database
          _ <- movies.traverse { movie =>
            sql"INSERT INTO movies (title, genre, release_year) VALUES (${movie.title}, ${movie.genre}, ${movie.releaseYear})"
              .update.run.transact(xa)
          }
          // Retrieve movies
          result <- repo.findAll
        } yield {
          // Perform assertions
          result.map(_.copy(id = None)) must contain allElementsOf movies
        }

        // Return the IO for testing
        ioTest
      }.unsafeRunSync() // Run the IO action

    }

  }
}
