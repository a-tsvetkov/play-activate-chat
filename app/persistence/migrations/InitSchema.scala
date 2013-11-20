package persistence.migrations

import models._
import net.fwbrasil.activate.migration.Migration
import persistence.ChatPersistenceContext._


class InitSchema extends Migration {

  def timestamp = System.currentTimeMillis

  def up = {
    createTableForAllEntities
      .ifNotExists
  }
}
