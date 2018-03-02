package org.wit.hillfort.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.hillfort.models.HillfortMemStore
import org.wit.hillfort.models.HillfortStore
import org.wit.hillfort.room.HillfortStoreRoom

class MainApp : Application(), AnkoLogger {

  lateinit var hillforts: HillfortStore

  override fun onCreate() {
    super.onCreate()
   // hillforts = hillfortMemStore()
    hillforts = HillfortStoreRoom (applicationContext)
    info("hillfort started")
  }
}