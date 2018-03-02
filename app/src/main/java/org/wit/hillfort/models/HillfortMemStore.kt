package org.wit.hillfort.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

internal fun getId(): Long {
  return lastId++
}

class HillfortMemStore : HillfortStore, AnkoLogger {

  val hillforts = ArrayList<HillfortModel>()

  suspend override fun findAll(): List<HillfortModel> {
    return hillforts
  }

  override fun create(hillfort: HillfortModel) {
    hillfort.id = getId()
    hillforts.add(hillfort)
    logAll()
  }

  override fun update(hillfort: HillfortModel) {
    var foundhillfort: HillfortModel? = hillforts.find { p -> p.id == hillfort.id }
    if (foundhillfort != null) {
      foundhillfort.title = hillfort.title
      foundhillfort.description= hillfort.description
      foundhillfort.image = hillfort.image
      foundhillfort.lat = hillfort.lat
      foundhillfort.lng = hillfort.lng
      foundhillfort.zoom = hillfort.zoom
    }
  }

  open fun clear(hillfort: HillfortModel)
  {
    hillforts.clear()
  }

  internal fun logAll() {
    hillforts.forEach { info("${it}") }
  }
}