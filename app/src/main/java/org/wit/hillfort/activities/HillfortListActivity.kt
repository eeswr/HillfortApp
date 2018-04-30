package org.wit.hillfort.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.*
import com.google.android.gms.maps.model.Marker
import kotlinx.android.synthetic.main.activity_hillfort_list.*
import kotlinx.android.synthetic.main.content_hillfort_maps.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.*
import org.wit.hillfort.R
import org.wit.hillfort.helpers.readImageFromPath
import org.wit.hillfort.main.MainApp
import org.wit.hillfort.models.HillfortModel

class HillfortListActivity : AppCompatActivity(), HillfortListener {

  lateinit var app: MainApp

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_hillfort_list)
    app = application as MainApp

    toolbarMain.title = title
    setSupportActionBar(toolbarMain)

    val layoutManager = LinearLayoutManager(this)
    recyclerView.layoutManager = layoutManager
    loadhillforts()
  }

  private fun loadhillforts() {
    async(UI) {
      showhillforts(app.hillforts.findAll())
    }
  }

  fun showhillforts(hillforts: List<HillfortModel>) {
    recyclerView.adapter = hillfortAdapter(hillforts, this)
    recyclerView.adapter.notifyDataSetChanged()
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu_main, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
    loadhillforts()
    super.onActivityResult(requestCode, resultCode, data)
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item?.itemId) {
      R.id.item_add -> startActivityForResult<HillfortActivity>(200)
      R.id.item_map -> startActivity<HillfortMapsActivity>()
    }
    return super.onOptionsItemSelected(item)
  }

  override fun onhillfortClick(hillfort: HillfortModel) {
    startActivityForResult(intentFor<HillfortActivity>().putExtra("hillfort_edit", hillfort), 201)
  }

  override fun onhillfortLongClick(hillfort: HillfortModel): Boolean {
    Log.i("ListActivity", "Long Pressed on hilfort " + hillfort.toString())

    alert("Delete hillfort?") {
      title = "Delete"
      yesButton {
        app.hillforts.delete(hillfort)
        loadhillforts()
        toast("hillfort deleted")
      }
      noButton { }
    }.show()

    return true
  }

}