package persistence

import com.github.mauricio.async.db.Configuration
import com.github.mauricio.async.db.postgresql.pool.PostgreSQLConnectionFactory
import net.fwbrasil.activate.ActivateContext
import net.fwbrasil.activate.storage.relational.async.AsyncPostgreSQLStorage

import play.api.Play


object ChatPersistenceContext extends ActivateContext {
  val storage = new AsyncPostgreSQLStorage {
    def configuration = {
      val maybeConfiguration = for {
        dbConfig <- Play.current.configuration.getConfig("activate.db")
        host <- dbConfig.getString("host")
        username <- dbConfig.getString("user")
      } yield new Configuration(
        username = username,
        host = host,
        password = dbConfig.getString("password"),
        database = dbConfig.getString("database")
      )

      maybeConfiguration.getOrElse {
          throw Play.current.configuration.reportError("db", "Database access configuration error")
        }
    }
    lazy val objectFactory = new PostgreSQLConnectionFactory(configuration)
    }

}
