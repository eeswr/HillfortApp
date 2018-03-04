package org.wit.hillfort.activities

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_hillfort.view.*
import kotlinx.android.synthetic.main.card_hillfort.view.*
import kotlinx.android.synthetic.main.card_hillfort.view.*
import org.wit.hillfort.R
import org.wit.hillfort.helpers.readImageFromPath
import org.wit.hillfort.models.HillfortModel

interface HillfortListener {
  fun onhillfortClick(hillfort: HillfortModel)
}

class hillfortAdapter constructor(private var hillforts: List<HillfortModel>,
                                   private val listener: HillfortListener) : RecyclerView.Adapter<hillfortAdapter.MainHolder>() {

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MainHolder {
    return MainHolder(LayoutInflater.from(parent?.context).inflate(R.layout.card_hillfort, parent, false))
  }

  override fun onBindViewHolder(holder: MainHolder, position: Int) {
    val hillfort = hillforts[holder.adapterPosition]
    holder.bind(hillfort, listener)
  }

  override fun getItemCount(): Int = hillforts.size

  class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(hillfort: HillfortModel,  listener : HillfortListener) {
      itemView.hillfortTitle1.text = hillfort.title
      //itemView.hillfortDescription.text = hillfort.description
      itemView.imageIcon.setImageBitmap(readImageFromPath(itemView.context, hillfort.image))
      itemView.setOnClickListener { listener.onhillfortClick(hillfort) }
    }
  }
}