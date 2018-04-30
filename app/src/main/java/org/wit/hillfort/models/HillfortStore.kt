package org.wit.hillfort.models

interface HillfortStore {
  suspend fun findAll(): List<HillfortModel>
  suspend fun findById(id:Long) : HillfortModel?
  fun create(hillfort: HillfortModel)
  fun update(hillfort: HillfortModel)
  fun delete(hillfort: HillfortModel)
}

