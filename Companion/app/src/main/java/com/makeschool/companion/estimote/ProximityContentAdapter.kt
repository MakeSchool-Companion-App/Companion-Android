package com.makeschool.companion.estimote

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.makeschool.companion.R
import java.util.*

class ProximityContentAdapter(private val context: Context) : BaseAdapter() {

    private var nearbyContent: List<ProximityContent> = ArrayList()

    fun setNearbyContent(nearbyContent: List<ProximityContent>) {
        this.nearbyContent = nearbyContent
    }

    override fun getCount(): Int {
        return nearbyContent.size
    }

    override fun getItem(position: Int): Any {
        return nearbyContent[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            convertView = inflater.inflate(R.layout.content_view, parent, false)
        }

        val content = nearbyContent[position]

        val title = convertView!!.findViewById<TextView>(R.id.title)
        val subtitle = convertView.findViewById<TextView>(R.id.subtitle)

        title.text = content.title
        subtitle.text = content.subtitle

        convertView.setBackgroundColor(Utils.getEstimoteColor(content.title))

        return convertView
    }
}
