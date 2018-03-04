package org.wit.hillfort.room

import android.arch.persistence.room.*
import org.wit.hillfort.models.HillfortModel

@Dao
interface HillfortDao {

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun create(hillfort: HillfortModel)

  @Query("SELECT * FROM hillfortModel")
  fun findAll(): List<HillfortModel>

  @Update
  fun update(hillfort: HillfortModel)

  @Delete
  fun delete(hillfort: HillfortModel)
}