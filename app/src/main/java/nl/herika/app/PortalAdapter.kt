package nl.herika.app

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_portal.view.*
import nl.herika.app.data.Portal

class PortalAdapter(private val portals: List<Portal>, private val clickListener:(Portal) -> Unit) :
    RecyclerView.Adapter<PortalAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun databind(portal: Portal, clickListener: (Portal) -> Unit) {
            itemView.tv_portal_title.text = portal.title
            itemView.tv_portal_link.text = portal.url
            itemView.portal_item.setOnClickListener{clickListener(portal)}
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_portal, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(portals[position], clickListener = clickListener)
    }

    override fun getItemCount(): Int {
        return portals.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        holder.databind(portals[position], clickListener)
    }
}