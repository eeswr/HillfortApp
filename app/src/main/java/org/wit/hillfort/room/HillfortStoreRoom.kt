package org.wit.hillfort.room

import android.arch.persistence.room.Room
import android.content.Context
import org.jetbrains.anko.coroutines.experimental.bg
import org.wit.hillfort.models.HillfortModel
import org.wit.hillfort.models.HillfortStore

class HillfortStoreRoom(val context: Context) : HillfortStore {

  var dao: HillfortDao

  init {
    val database = Room.databaseBuilder(context, Database::class.java, "room_sample.db")
        .fallbackToDestructiveMigration()
        .build()
    dao = database.hillfortDao()
  }

  override suspend fun findAll(): List<HillfortModel> {
    val deferredhillforts = bg {
      dao.findAll()
    }
    val hillforts = deferredhillforts.await()
    return hillforts
  }

  override fun create(hillfort: HillfortModel) {
    bg {
      dao.create(hillfort)
    }
  }

  override fun update(hillfort: HillfortModel) {
    bg {
      dao.update(hillfort)
    }
  }

  override fun delete(hillfort: HillfortModel) {
    bg {
      dao.delete(hillfort)
    }
  }
}